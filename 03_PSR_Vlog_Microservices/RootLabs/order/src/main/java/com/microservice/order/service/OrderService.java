package com.microservice.order.service;

import com.microservice.inventory.dto.InventoryDTO;
import com.microservice.order.common.ErrorOrderResponse;
import com.microservice.order.common.OrderResponse;
import com.microservice.order.common.SuccessOrderResponse;
import com.microservice.order.dto.OrderDTO;
import com.microservice.order.model.Orders;
import com.microservice.order.repo.OrderRepo;
import com.microservice.product.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
@Transactional
public class OrderService {

    //declare webclient here
    private final WebClient inventoryWebClient;
    private final WebClient productWebClient;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    //create constructor for webclient in this service
    public OrderService(WebClient inventoryWebClient, WebClient productWebClient, OrderRepo orderRepo, ModelMapper modelMapper) {
        this.inventoryWebClient = inventoryWebClient;
        this.productWebClient = productWebClient;
        this.orderRepo = orderRepo;
        this.modelMapper = modelMapper;
    }

    public List<OrderDTO> getAllOrders() {
        List<Orders>orderList = orderRepo.findAll();
        return modelMapper.map(orderList, new TypeToken<List<OrderDTO>>(){}.getType());
    }

    // Condition for saving Order
    // 1 - check item is available in inventory
    // 2 - then inventory table that item quantity greater than 0
    // 3 - then check product table that item is for sale or not
    // if item available and quantity greater than 0 and product is to be for sale, then we can save that order to database
    public OrderResponse saveOrder(OrderDTO OrderDTO) {

        // 1 - create webclient communication with inventory application for check item is available and quantity grater than 0

            // Catch ItemId in OrderDto
            Integer orderItemId = OrderDTO.getItemId();


            try {
                // Call Web Service for Fetch Inventory
                // Get Inventory as dependency in pom.xml file then we can use Inventory classes
                InventoryDTO inventoryResponse =  inventoryWebClient.get()
                        .uri(uriBuilder -> uriBuilder.path("/item/{itemId}").build(orderItemId))
                        .retrieve()
                        .bodyToMono(InventoryDTO.class)
                        .block();

                //check quantity is nul
                assert inventoryResponse != null;

                // Catch ProductId In InventoryDTO
                Integer inventoryProductId = inventoryResponse.getProductId();

                // Call Web Service for Fetch Product
                // Get Product as dependency in pom.xml file then we can use Product classes
                ProductDTO productResponse =  productWebClient.get()
                        .uri(uriBuilder -> uriBuilder.path("/product/{productId}").build(inventoryProductId))
                        .retrieve()
                        .bodyToMono(ProductDTO.class)
                        .block();

                //check product is null
                assert productResponse != null;

                //use inventoryResponse and check quantity greater than 0
                if(inventoryResponse.getQuantity() > 0) {

                    //use productResponse and check forSale true or not
                    if(productResponse.getForSale() == 1){
                        orderRepo.save(modelMapper.map(OrderDTO, Orders.class));
                    }
                    else {
                        return new ErrorOrderResponse("This item not for sale");
                    }
                    return new SuccessOrderResponse(OrderDTO);
                }
                else {
                    return new ErrorOrderResponse("Item not available, please try later");
                }
            }
            catch (WebClientResponseException e) {
                if(e.getStatusCode().is5xxServerError()){
                    return new ErrorOrderResponse("Item not found");
                }
            }
            return null;
    }

    public OrderDTO updateOrder(OrderDTO OrderDTO) {
        orderRepo.save(modelMapper.map(OrderDTO, Orders.class));
        return OrderDTO;
    }

    public String deleteOrder(Integer orderId) {
        orderRepo.deleteById(orderId);
        return "Order deleted";
    }
    public OrderDTO getOrderById(Integer orderId) {
        Orders order = orderRepo.getOrderById(orderId);
        return modelMapper.map(order, OrderDTO.class);
    }
}

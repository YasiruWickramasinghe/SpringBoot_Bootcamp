package com.microservice.order.service;

import com.microservice.inventory.dto.InventoryDTO;
import com.microservice.order.common.ErrorOrderResponse;
import com.microservice.order.common.OrderResponse;
import com.microservice.order.common.SuccessOrderResponse;
import com.microservice.order.dto.OrderDTO;
import com.microservice.order.model.Orders;
import com.microservice.order.repo.OrderRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Transactional
public class OrderService {

    //declare webclient here
    private final WebClient webClient;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    //create constructor for webclient in this service
    public OrderService(WebClient.Builder webClientBuilder, OrderRepo orderRepo, ModelMapper modelMapper) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/api/v1").build();
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

            // Call Web Service for Fetch Inventory
            // Get Inventory as dependency in pom.xml file then we can use Inventory classes
            try {
                InventoryDTO inventoryResponse =  webClient.get()
                        .uri(uriBuilder -> uriBuilder.path("/item/{itemId}").build(orderItemId))
                        .retrieve()
                        .bodyToMono(InventoryDTO.class)
                        .block();

                //check quantity is nul
                assert inventoryResponse != null;

                //use inventoryResponse and check quantity greater than 0
                if(inventoryResponse.getQuantity() > 0) {
                    orderRepo.save(modelMapper.map(OrderDTO, Orders.class));
                    return new SuccessOrderResponse(OrderDTO);
                }
                else {
                    return new ErrorOrderResponse("Item not available, please try later");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
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

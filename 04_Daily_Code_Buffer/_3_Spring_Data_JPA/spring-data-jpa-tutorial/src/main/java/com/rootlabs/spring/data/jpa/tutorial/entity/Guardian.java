package com.rootlabs.spring.data.jpa.tutorial.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Using this @Embeddable class we do not create new entity called "Guardian" but inside Student entity we can use this
//we use this because we don't need table called Guardian
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// Map the properties with database column names
@AttributeOverrides({
        @AttributeOverride(
                name = "name",
                column = @Column(name = "guardian_name")
        ),
        @AttributeOverride(
                name = "email",
                column = @Column(name = "guardian_email")
        ),
        @AttributeOverride(
                name = "mobile",
                column = @Column(name = "guardian_mobile")
        ),
})
public class Guardian {

    private String name;
    private String email;
    private String mobile;
}

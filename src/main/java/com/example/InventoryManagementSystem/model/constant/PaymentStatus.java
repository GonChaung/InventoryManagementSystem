package com.example.InventoryManagementSystem.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentStatus implements BaseEnum<Integer>{

        PAID(1),
        UNPAID(2);

        private final int value;

        @Override
        public Integer getValue() {
            return value;
        }
}

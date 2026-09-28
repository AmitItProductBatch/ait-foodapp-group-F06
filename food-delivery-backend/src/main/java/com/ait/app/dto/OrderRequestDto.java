package com.ait.app.dto;

public class OrderRequestDto {
	
	
	    private int addressId;
	    private String paymentMethod;
	    private int userId;
	    private int restaurantId;
	    private String deliveryInstructions;
	    private double totalAmount;
	    
	    

		public double getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}

		public String getDeliveryInstructions() {
			return deliveryInstructions;
		}

		public void setDeliveryInstructions(String deliveryInstructions) {
			this.deliveryInstructions = deliveryInstructions;
		}

		public int getRestaurantId() {
			return restaurantId;
		}

		public void setRestaurantId(int restaurantId) {
			this.restaurantId = restaurantId;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public int getAddressId() {
	        return addressId;
	    }

	    public void setAddressId(int addressId) {
	        this.addressId = addressId;
	    }

	    public String getPaymentMethod() {
	        return paymentMethod;
	    }

	    public void setPaymentMethod(String paymentMethod) {
	        this.paymentMethod = paymentMethod;
	    }
	}



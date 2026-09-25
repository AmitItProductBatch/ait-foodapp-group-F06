package com.ait.app.GlobalExceptionHandler;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ait.app.exception.AddressNotFoundException;

import com.ait.app.exception.CartItemNotFoundException;
import com.ait.app.exception.CartItemServiceException;
import com.ait.app.exception.CartNotFoundException;
import com.ait.app.exception.CustomerAddressException;
import com.ait.app.exception.CustomerException;

import com.ait.app.exception.CustomerProfileException;
import com.ait.app.exception.DeliveryFeeRuleNotFoundException;
import com.ait.app.exception.DeliveryRadiusExceededException;
import com.ait.app.exception.DuplicateRoleException;
import com.ait.app.exception.FoodCategoryException;
import com.ait.app.exception.FoodItemException;
import com.ait.app.exception.FoodItemNotFoundException;
import com.ait.app.exception.InvalidQuantityException;
import com.ait.app.exception.RestaurantAddressNotFoundException;
import com.ait.app.exception.RestaurantException;
import com.ait.app.exception.RoleValidationException;
import com.ait.app.exception.UpdateCustomerProfileException;
import com.ait.app.exception.UserNotFoundException;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<String> handleCustomerException(CustomerException customerException) {

		return new ResponseEntity<>(customerException.getErrorMessage(), customerException.getHttpStatus());
	}

	@ExceptionHandler(CustomerAddressException.class)
	ResponseEntity<String> handleCustomerAddressException(CustomerAddressException customerAddressException) {

		return new ResponseEntity(customerAddressException.getErrorMessage(), customerAddressException.getHttpStatus());
	}

	@ExceptionHandler(CustomerProfileException.class)
	ResponseEntity<String> handleCustomerProfileException(CustomerProfileException customerProfileException) {

		return new ResponseEntity(customerProfileException.getErrorMessage(), customerProfileException.getHttpStatus());
	}

	@ExceptionHandler(UpdateCustomerProfileException.class)
	ResponseEntity<String> handleUpdateCustomerProfileException(
			UpdateCustomerProfileException updateCustomerProfileException) {
		return new ResponseEntity(updateCustomerProfileException.getErrorMessage(),
				updateCustomerProfileException.getHttpStatus());
	}

	@ExceptionHandler(RestaurantException.class)
	ResponseEntity<String> handleRestaurantException(RestaurantException restaurantException) {
		return new ResponseEntity<String>(restaurantException.getErrorMessage(), restaurantException.getHttpStatus());
	}

	@ExceptionHandler(UserNotFoundException.class)
	ResponseEntity<String> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
		return new ResponseEntity<String>(userNotFoundException.getErrorMessage(),
				userNotFoundException.getHttpStatus());
	}

	@ExceptionHandler(DuplicateRoleException.class)
	public ResponseEntity<String> handleDuplicateRoleException(DuplicateRoleException duplicateRoleException) {

		return new ResponseEntity<>(duplicateRoleException.getErroeMessage(), duplicateRoleException.getHttpStatus());

	}

	@ExceptionHandler(RoleValidationException.class)
	public ResponseEntity<String> RoleValidationException(RoleValidationException roleValidationException) {
		return new ResponseEntity<>(roleValidationException.getMessage(), roleValidationException.getStatus());

	}

	@ExceptionHandler(FoodItemException.class)
	public ResponseEntity<String> handleFooditemException(FoodItemException foodItemException) {
		return new ResponseEntity(foodItemException.getErrorMessage(), foodItemException.getHttpStatus());
	}

	@ExceptionHandler(FoodItemNotFoundException.class)
	ResponseEntity<String> handleFoodItemNotFoundException(FoodItemException foodItemException) {
		return new ResponseEntity(foodItemException.getErrorMessage(), foodItemException.getHttpStatus());
	}

	@ExceptionHandler(CartItemServiceException.class)
	ResponseEntity<String> CartItemServiceException(CartItemServiceException cartItemServiceException) {
		return new ResponseEntity(cartItemServiceException.getMessage(), cartItemServiceException.getHttpStatus());
	}

	@ExceptionHandler(CartNotFoundException.class)
	public ResponseEntity<String> handleCartNotFound(CartNotFoundException cartNotFoundException) {

		return new ResponseEntity(cartNotFoundException.getMessage(), cartNotFoundException.getStatus());

	}

	@ExceptionHandler(CartItemNotFoundException.class)
	public ResponseEntity<String> handleCartItemNotFoundException(CartItemNotFoundException cartItemNotFoundException) {

		return new ResponseEntity(cartItemNotFoundException.getErrorMessage(),
				cartItemNotFoundException.getHttpStatus());

	}

	@ExceptionHandler(InvalidQuantityException.class)
	public ResponseEntity<String> handleInvalidQuantityException(InvalidQuantityException invalidQuantityException) {

		return new ResponseEntity(invalidQuantityException.getErrorMessage(), invalidQuantityException.getHttpStatus());

	}

	@ExceptionHandler(FoodCategoryException.class)
	public ResponseEntity<String> handleFoodCategoryException(FoodCategoryException e) {

		return new ResponseEntity(e.getMessage(), e.getStatus());

	}

	@ExceptionHandler(RestaurantAddressNotFoundException.class)
	public ResponseEntity<String> handleRestaurantAddressNotFoundException(
			RestaurantAddressNotFoundException restaurantAddressNotFoundException) {

		return new ResponseEntity(restaurantAddressNotFoundException.getErrormessage(),
				restaurantAddressNotFoundException.getHttpStatus());

	}

	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<String> handleAddressNotFound(AddressNotFoundException ex) {
		return new ResponseEntity<>(ex.getErrorMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DeliveryRadiusExceededException.class)
	public ResponseEntity<String> handleDeliveryRadiusExceededException(
			DeliveryRadiusExceededException deliveryRadiusExceededException) {
		return new ResponseEntity<String>(deliveryRadiusExceededException.getErrorMessage(),
				deliveryRadiusExceededException.getHttpStatus());
	}

	@ExceptionHandler(DeliveryFeeRuleNotFoundException.class)
	public ResponseEntity<String> handleDeliveryFeeRuleNotFoundException(
			DeliveryFeeRuleNotFoundException deliveryFeeRuleNotFoundException) {
		return new ResponseEntity<String>(deliveryFeeRuleNotFoundException.getErrormessage(),
				deliveryFeeRuleNotFoundException.getHttpStatus());
	}

}

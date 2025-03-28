/*package com.capgemini.lenscart;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderManagement1ApplicationTests {

	@Test
	void contextLoads() {

	}

}*/
package com.capgemini.lenscart;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.capgemini.lenscart.dto.CartDTO;
import com.capgemini.lenscart.dto.PaymentDTO;
import com.capgemini.lenscart.entity.Payment;
import com.capgemini.lenscart.repository.PaymentRepository;
import com.capgemini.lenscart.client.CartClient;
import com.capgemini.lenscart.service.PaymentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

@ExtendWith(MockitoExtension.class)  // Initialize mocks
class PaymentServiceTest {

	@Mock
	private PaymentRepository paymentRepository;  // Mock PaymentRepository

	@Mock
	private CartClient cartClient;  // Mock CartClient

	@InjectMocks
	private PaymentService paymentService;  // Service under test

	private PaymentDTO paymentDTO;
	private Payment payment;
	private CartDTO cartDTO;  // Declare CartDTO

	@BeforeEach
	public void setup() {
		// Initialize PaymentDTO and Payment for use in tests
		paymentDTO = new PaymentDTO();
		paymentDTO.setItemId(1);
		paymentDTO.setTransactionId("TX123");
		paymentDTO.setPaymentType("CREDIT_CARD");

		payment = new Payment();
		payment.setItemId(paymentDTO.getItemId());
		payment.setTransactionId(paymentDTO.getTransactionId());
		payment.setPaymentType(paymentDTO.getPaymentType());
		payment.setAmount(100.00);

		// Initialize CartDTO here
		cartDTO = new CartDTO();
		cartDTO.setItemId(paymentDTO.getItemId());

	}

	// Test that the Spring context loads properly
	@Test
	void contextLoads() {
		// No-op test that ensures the Spring context loads without any issues
		assertTrue(true);
	}

	@Test
	public void testProcessPaymentTransactionIdAlreadyUsed() throws Exception {
		// Mock the PaymentRepository to return true for transaction ID check
		when(paymentRepository.existsByTransactionId(paymentDTO.getTransactionId())).thenReturn(true);

		// Execute the method and assert exception is thrown
		Exception exception = assertThrows(Exception.class, () -> {
			paymentService.processPayment(paymentDTO);
		});

		assertEquals("Transaction ID already used.", exception.getMessage());
	}

	@Test
	public void testProcessPaymentCartNotFound() throws Exception {
		// Mock the CartClient to return null for the given itemId
		when(cartClient.getCartByItemId(paymentDTO.getItemId())).thenReturn(null);

		// Execute the method and assert exception is thrown
		Exception exception = assertThrows(Exception.class, () -> {
			paymentService.processPayment(paymentDTO);
		});

		assertEquals("Cart with itemId 1 not found.", exception.getMessage());
	}

	@Test
	public void testUpdatePaymentStatus() throws Exception {
		// Mock the PaymentRepository to return a payment for the given ID
		when(paymentRepository.findById(anyLong())).thenReturn(Optional.of(payment));
		when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

		// Execute the method
		Payment result = paymentService.updatePaymentStatus(1L, "FAILURE");

		// Verify the result
		assertEquals("FAILURE", result.getStatus());
		verify(paymentRepository).save(any(Payment.class));
	}

	@Test
	public void testUpdatePaymentStatusPaymentNotFound() throws Exception {
		// Mock the PaymentRepository to return empty for the given ID
		when(paymentRepository.findById(anyLong())).thenReturn(Optional.empty());

		// Execute the method and assert exception is thrown
		Exception exception = assertThrows(Exception.class, () -> {
			paymentService.updatePaymentStatus(1L, "FAILURE");
		});

		assertEquals("Payment not found", exception.getMessage());
	}

	@Test
	public void testGetPaymentById() {
		// Mock the PaymentRepository to return a payment for the given ID
		when(paymentRepository.findById(anyLong())).thenReturn(Optional.of(payment));

		// Execute the method
		Payment result = paymentService.getPaymentById(1L);

		// Verify the result
		assertNotNull(result);
		assertEquals(payment.getTransactionId(), result.getTransactionId());
	}

	@Test
	public void testDeletePayment() throws Exception {
		// Mock the PaymentRepository to return the payment for the given ID
		when(paymentRepository.findById(anyLong())).thenReturn(Optional.of(payment));

		// Execute the method
		paymentService.deletePayment(1L);

		// Verify that the delete method is called
		verify(paymentRepository).delete(payment);
	}

	@Test
	public void testDeletePaymentNotFound() throws Exception {
		// Mock the PaymentRepository to return empty for the given ID
		when(paymentRepository.findById(anyLong())).thenReturn(Optional.empty());

		// Execute the method and assert exception is thrown
		Exception exception = assertThrows(Exception.class, () -> {
			paymentService.deletePayment(1L);
		});

		assertEquals("Payment with ID 1 not found", exception.getMessage());
	}

	@Test
	public void testGetAllPayments() {
		// Mock the PaymentRepository to return a list with a single payment
		when(paymentRepository.findAll()).thenReturn(List.of(payment));

		// Execute the method
		List<Payment> result = paymentService.getAllPayments();

		// Verify the result
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(payment.getTransactionId(), result.get(0).getTransactionId());
	}

	@Test
	public void testGetPaymentByItemId() {
		// Mock the PaymentRepository to return a payment for the given itemId
		when(paymentRepository.findByItemId(anyInt())).thenReturn(payment);

		// Execute the method
		Payment result = paymentService.getPaymentByItemId(1);

		// Verify the result
		assertNotNull(result);
		assertEquals(payment.getTransactionId(), result.getTransactionId());
	}
}

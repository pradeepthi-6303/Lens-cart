package com.capgemini.lenscart.controller;
/*

import com.capgemini.lenscart.dto.PaymentDTO;
import com.capgemini.lenscart.entity.Payment;
import com.capgemini.lenscart.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@Validated
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/create")
    public Payment createPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setOrderId(paymentDTO.getOrderId());
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setPaymentType(paymentDTO.getPaymentType());
        payment.setAmount(paymentDTO.getAmount());
        payment.setStatus(paymentDTO.getStatus());
        payment.setUserId(paymentDTO.getUserId());
        return paymentService.savePayment(payment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
*/


import com.capgemini.lenscart.client.CartClient;
import com.capgemini.lenscart.dto.CartDTO;
import com.capgemini.lenscart.dto.PaymentDTO;
import com.capgemini.lenscart.entity.Payment;
import com.capgemini.lenscart.exceptions.InvalidPaymentException;
import com.capgemini.lenscart.exceptions.PaymentException;
import com.capgemini.lenscart.exceptions.PaymentNotFoundException;
import com.capgemini.lenscart.service.PaymentService;
import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/*
@RestController
@RequestMapping("/payments")
@Validated
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/get")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/create")
    public ResponseEntity<Payment> createPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        Payment payment = paymentService.savePayment(paymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}

*/
/*
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Create a new payment (POST)
    @PostMapping("/save")
    public String savePayment(@RequestBody @Valid PaymentDTO paymentDTO) {
        return paymentService.savePayment(paymentDTO);
    }

    // Get payment by ID (GET)
    @GetMapping("/{id}")
    public String getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    // Get all payments (GET)
    @GetMapping
    public String getAllPayments() {
        return paymentService.getAllPayments();
    }

    // Delete payment by ID (DELETE)
    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }

    // Update payment by ID (PUT)
    @PutMapping("/{id}")
    public String updatePayment(@PathVariable Long id, @RequestBody @Valid PaymentDTO paymentDTO) {
        // Check if payment exists
        if (!paymentService.existsById(id)) {
            return "Payment not found with id: " + id;
        }

        // Update the payment details
        return paymentService.updatePayment(id, paymentDTO);
    }
}
*//*
@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentDTO createPayment(@RequestBody PaymentDTO paymentDTO) {
        return paymentService.savePayment(paymentDTO);
    }

    @GetMapping("/{id}")
    public PaymentDTO getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping
    public List<PaymentDTO> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @PutMapping("/{id}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public PaymentDTO cancelPayment(@PathVariable Long id) {
        return paymentService.cancelPayment(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }
}
*/
/*
@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/createpost")

    public ResponseEntity<?> createPayment(@RequestBody PaymentDTO paymentDTO) {
        try {
            Payment createdPayment = paymentService.savePayment(paymentDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
        } catch (InvalidPaymentException ex) {
            // Return validation error messages in the response body
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

/*
    public ResponseEntity<PaymentDTO> createPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        PaymentDTO createdPayment = paymentService.savePayment(paymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentDTO paymentDTO) {
        PaymentDTO updatedPayment = paymentService.updatePayment(id, paymentDTO);
        return ResponseEntity.ok(updatedPayment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long id) {
        PaymentDTO paymentDTO = paymentService.getPaymentById(id);
        return ResponseEntity.ok(paymentDTO);
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        List<PaymentDTO> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
*/
/*

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // POST request to create a payment
    @PostMapping("/createpost")
    public ResponseEntity<?> createPayment(@RequestBody PaymentDTO paymentDTO) {
        try {
            Payment createdPayment = paymentService.savePayment(paymentDTO);

            // Returning the created payment details with status and message
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
        } catch (InvalidPaymentException ex) {
            // Return validation error messages in the response body
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    // PUT request to update a payment
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable Long id, @RequestBody PaymentDTO paymentDTO) {
        try {
            Payment updatedPayment = paymentService.updatePayment(id, paymentDTO);
            return ResponseEntity.status(HttpStatus.OK).body(updatedPayment);
        } catch (PaymentNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", ex.getMessage()));
        } catch (InvalidPaymentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    // GET request to get payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
        try {
            Payment payment = paymentService.getPaymentById(id);
            return ResponseEntity.status(HttpStatus.OK).body(payment);
        } catch (PaymentNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    // DELETE request to delete payment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable Long id) {
        try {
            paymentService.deletePayment(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.singletonMap("message", "Payment deleted successfully."));
        } catch (PaymentNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }
}

/*
@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Create payment (POST mapping)
    @PostMapping
    public ResponseEntity<?> createPayment(@RequestBody @Valid PaymentDTO paymentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Collect validation error messages
            List<String> validationErrors = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", String.join(", ", validationErrors)));
        }

        try {
            Payment createdPayment = paymentService.savePayment(paymentDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
        } catch (InvalidPaymentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }

    // Get payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            return ResponseEntity.ok(payment.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Get all payments
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Delete payment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            paymentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Payment not found."));
        }
    }

    // PUT mapping for updating payment details
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable Long id, @RequestBody @Valid PaymentDTO paymentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Collect validation error messages
            List<String> validationErrors = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", String.join(", ", validationErrors)));
        }

        try {
            Payment updatedPayment = paymentService.updatePayment(id, paymentDTO);
            return ResponseEntity.ok(updatedPayment);
        } catch (PaymentNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", ex.getMessage()));
        } catch (InvalidPaymentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
        }
    }
}
*/
/*
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
/*
    // 1. Create a new payment
    @PostMapping  ("/createpayment")
    public ResponseEntity<Object> createPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        try {
            Payment payment = paymentService.createPayment(paymentDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(payment);
        } catch (PaymentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 2. Get a payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPaymentById(@PathVariable Long id) {
        Optional<Payment> payment = paymentService.getPaymentById(id);
        if (payment.isPresent()) {
            return ResponseEntity.ok(payment.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment not found");
        }
    }

    // 3. Get all payments (for admin or reporting purposes)
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    // 4. Update an existing payment by ID
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentDTO paymentDTO) {
        try {
            Payment updatedPayment = paymentService.updatePayment(id, paymentDTO);
            return ResponseEntity.ok(updatedPayment);
        } catch (PaymentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 5. Delete a payment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePayment(@PathVariable Long id) {
        try {
            paymentService.deletePayment(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Payment deleted successfully");
        } catch (PaymentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment not found");
        }
    }
}
*/
    /*

    @PostMapping("/createpayment")
    public ResponseEntity<Object> createPayment(@Valid @RequestBody PaymentDTO paymentDTO, BindingResult bindingResult) {
        // Check if there are any validation errors
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();

            // Collect all error messages
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errorMessages); // Return all error messages
        }

        // Check if transactionId already exists
        if (paymentRepository.existsByTransactionId(paymentDTO.getTransactionId())) {
            return ResponseEntity.badRequest().body("Transaction ID already exists");
        }

        // Check if orderId already exists
        if (paymentRepository.existsByOrderId(paymentDTO.getOrderId())) {
            return ResponseEntity.badRequest().body("Order ID already exists");
        }

        // Check if userId already exists
        if (paymentRepository.existsByUserId(paymentDTO.getUserId())) {
            return ResponseEntity.badRequest().body("User ID already exists");
        }

        // If no validation errors, save the payment
        Payment payment = new Payment();
        // Map DTO to entity and save
        paymentRepository.save(payment);

        return ResponseEntity.ok("Payment created successfully");
    }
}
*/
/*
    @RestController
    @RequestMapping("/api/payments")
    public class PaymentController {

        @Autowired
        private PaymentService paymentService;

        @PostMapping("/createpayment")
        public ResponseEntity<?> createPayment(@RequestBody @Valid PaymentDTO paymentDTO) {
            try {
                Payment payment = paymentService.processPayment(paymentDTO);
                return ResponseEntity.ok(payment);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage()); // Return an error message in the body
            }
        }

      // @PutMapping("/{id}/status")
        public ResponseEntity<?> updatePaymentStatus(@PathVariable Long id, @RequestParam String status) {
            try {
                Payment updatedPayment = paymentService.updatePaymentStatus(id, status);
                return ResponseEntity.ok(updatedPayment);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage()); // Return an error message in the body
            }
        //}
        @PutMapping("/{id}/status")
        public Payment updatePaymentStatus(@PathVariable Long id, @RequestBody PaymentDTO paymentDTO) throws Exception {
            return paymentService.updatePaymentStatus(id, paymentDTO.getStatus());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
            Payment payment = paymentService.getPaymentById(id);
            if (payment == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(payment);
        }

        @GetMapping
        public ResponseEntity<List<Payment>> getAllPayments() {
            List<Payment> payments = paymentService.getAllPayments();
            return ResponseEntity.ok(payments);
        }
        // DELETE method to delete a payment by its ID
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deletePayment(@PathVariable Long id) {
            try {
                paymentService.deletePayment(id);  // Call the service to delete payment
                return ResponseEntity.ok("Payment with ID " + id + " deleted successfully");
            } catch (Exception e) {
                return ResponseEntity.status(404).body(e.getMessage());
            }
        }

       @GetMapping("/by-cart/{cartId}")
        public Payment getPaymentByCartId(@PathVariable Integer cartId) {
            return paymentService.getPaymentDetailsByCartId(cartId);
        }

    }

*/
/*
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/createpayment")
    public ResponseEntity<?> createPayment(@RequestBody @Valid PaymentDTO paymentDTO) {
        try {
            Payment payment = paymentService.processPayment(paymentDTO);
            return ResponseEntity.ok(payment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/status")
    public Payment updatePaymentStatus(@PathVariable Long id, @RequestBody PaymentDTO paymentDTO) throws Exception {
        return paymentService.updatePaymentStatus(id, paymentDTO.getStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(payment);
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        try {
            paymentService.deletePayment(id);
            return ResponseEntity.ok("Payment with ID " + id + " deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
/*
    @GetMapping("/by-cart/{itemId}")
    public Payment getPaymentByItemId(@PathVariable Long itemId) {  // Updated to itemId
        return paymentService.getPaymentDetailsByItemId(itemId);
    }


}
*//*@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/createpayment")
    public ResponseEntity<?> createPayment(@RequestBody @Valid PaymentDTO paymentDTO) {
        try {
            Payment payment = paymentService.processPayment(paymentDTO);
            return ResponseEntity.ok(payment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());  // Return an error message in the body
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updatePaymentStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            Payment updatedPayment = paymentService.updatePaymentStatus(id, status);
            return ResponseEntity.ok(updatedPayment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());  // Return an error message in the body
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(payment);
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    // DELETE method to delete a payment by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        try {
            paymentService.deletePayment(id);  // Call the service to delete payment
            return ResponseEntity.ok("Payment with ID " + id + " deleted successfully");
        } catch (Exception e) {
            //return ResponseEntity.status(404).body(e.getMessage());
            return ResponseEntity.badRequest().body("Failed to delete payment with ID " + id + ": " + e.getMessage());
        }
    }
}
*/
/*
@RestController
@RequestMapping("/api/payments")
public class PaymentController {
@Autowired
private PaymentService paymentService;

@PostMapping("/createpayment")
public ResponseEntity<?> createPayment(@RequestBody @Valid PaymentDTO paymentDTO) {
    try {
        Payment payment = paymentService.processPayment(paymentDTO);
        return ResponseEntity.ok(payment);
    } catch (Exception e) {
        // Logging the error (consider using a logger like SLF4J here)
        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }
}

@PutMapping("/{id}/status")
public ResponseEntity<?> updatePaymentStatus(@PathVariable Long id, @RequestParam String status) {
    try {
        Payment updatedPayment = paymentService.updatePaymentStatus(id, status);
        return ResponseEntity.ok(updatedPayment);
    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }
}

@GetMapping("/{id}")
public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
    Payment payment = paymentService.getPaymentById(id);
    if (payment == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(payment);
}

@GetMapping
public ResponseEntity<List<Payment>> getAllPayments() {
    List<Payment> payments = paymentService.getAllPayments();
    return ResponseEntity.ok(payments);
}

@DeleteMapping("/{id}")
public ResponseEntity<String> deletePayment(@PathVariable Long id) {
    try {
        paymentService.deletePayment(id);
        return ResponseEntity.ok("Payment with ID " + id + " deleted successfully");
    } catch (Exception e) {
        // Logging the error (consider using a logger like SLF4J here)
        return ResponseEntity.badRequest().body("Failed to delete payment with ID " + id + ": " + e.getMessage());
    }
}
}
*/
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CartClient cartClient;

    @PostMapping("/createpayment")
    public ResponseEntity<?> createPayment(@RequestBody @Valid PaymentDTO paymentDTO) {
        try {
            Payment payment = paymentService.processPayment(paymentDTO);
            return ResponseEntity.ok(payment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updatePaymentStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            Payment updatedPayment = paymentService.updatePaymentStatus(id, status);
            return ResponseEntity.ok(updatedPayment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(payment);
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        try {
            paymentService.deletePayment(id);
            return ResponseEntity.ok("Payment with ID " + id + " deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to delete payment with ID " + id + ": " + e.getMessage());
        }

    }
    @GetMapping("/cart/{itemId}")
    public ResponseEntity<?> getCartAndPaymentDetails(@PathVariable("itemId") int itemId) {
        try {
            // Fetch Cart details using CartClient
            CartDTO cartDTO = null;
            try {
                cartDTO = cartClient.getCartByItemId(itemId);
            } catch (FeignException.NotFound e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Cart not found with itemId " + itemId);
            }

            // Fetch Payment details using PaymentService
            Payment payment = paymentService.getPaymentByItemId(itemId);
            if (payment == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Payment for itemId " + itemId + " not found.");
            }

            // Return cart and payment details as a response
            Map<String, Object> response = new HashMap<>();
            response.put("cart", cartDTO);
            response.put("payment", payment);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

}
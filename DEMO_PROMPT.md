# Demo Test Prompt for AI Consistency Testing

Use this exact prompt in each developer worktree to test code generation consistency.

---

## Test Prompt (Copy this exactly)

```
Create a complete Booking CRUD service for VTrip with the following requirements:

Entity: Booking
- customerId (Long, required)
- checkInDate (LocalDate, required)  
- checkOutDate (LocalDate, required)
- totalAmount (BigDecimal, required)
- status (enum: PENDING, CONFIRMED, CANCELLED, COMPLETED)

Create all files following the project standards:
1. Entity with JPA annotations
2. Status enum
3. CreateBookingRequestDto with validation
4. UpdateBookingRequestDto for partial updates
5. BookingResponseDto
6. BookingMapper with MapStruct
7. BookingRepository
8. BookingService interface
9. BookingServiceImpl with logging
10. BookingController with OpenAPI annotations
11. NotFoundException with forEntity() factory method

Follow all rules in .cursor/rules/ exactly.
```

---

## Expected Consistency Points

After running on all 5 developers, verify these are IDENTICAL:

| File | Consistency Check |
|------|-------------------|
| Booking.java | Instant timestamps, @Enumerated, @Version |
| BookingStatus.java | Enum with 4 values |
| BookingMapper.java | 3 methods, full @Mapper config |
| BookingServiceImpl.java | @Slf4j, Page<T>, NotFoundException.forEntity() |
| BookingController.java | All OpenAPI annotations, Page<T> |
| NotFoundException.java | forEntity() factory method |

---

## Verification Script

After code generation, run from main project:
```bash
./scripts/verify-2-devs.sh
```

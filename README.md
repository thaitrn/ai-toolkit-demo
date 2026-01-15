# AI Toolkit Demo - Code Consistency với Cursor Rules

> **Demo:** 3 developers sử dụng AI (Cursor) để generate cùng một Booking CRUD service → Code được tạo ra **100% IDENTICAL** nhờ AI Toolkit rules.

---

## Vấn đề

Khi nhiều developers sử dụng AI để generate code, mỗi người sẽ nhận được code khác nhau:

| Developer | Timestamp | DI Pattern | Pagination | Exception |
|-----------|-----------|------------|------------|-----------|
| Dev 1 | `LocalDateTime` | `@Autowired` | `List<T>` | `RuntimeException` |
| Dev 2 | `Instant` | `@RequiredArgsConstructor` | `Page<T>` | `NotFoundException` |
| Dev 3 | `Date` | Field injection | `List<T>` | `IllegalArgumentException` |

→ **Code không consistent, khó maintain, review khó khăn.**

---

## Giải pháp: AI Toolkit Rules

Định nghĩa rules chi tiết trong `.cursor/rules/` để AI luôn generate code theo cùng một pattern:

```
.cursor/rules/
├── 00-strict-standards.mdc    # Master rule - patterns bắt buộc
├── 01-core-architecture.mdc   # Package structure
├── 04-mapstruct-mapper.mdc    # MapStruct configuration
├── 06-error-handling.mdc      # Exception patterns
├── 07-api-documentation.mdc   # OpenAPI annotations
├── 09-code-style.mdc          # Import order, Lombok style
└── templates/                 # Generic templates
```

---

## Kết quả Demo

```
╔══════════════════════════════════════════════════════════════════════════╗
║   ✅ SUCCESS: All 3 developers have IDENTICAL CODE!                     ║
║                                                                          ║
║   dev1-minh = dev2-linh = dev3-hung                                     ║
║                                                                          ║
║   AI Toolkit rules enforced 100% consistency!                           ║
╚══════════════════════════════════════════════════════════════════════════╝
```

| File | MD5 Hash | Status |
|------|----------|--------|
| BookingController.java | `4b0d6e15fc3c17ff1a548325308f79ad` | ALL MATCH |
| BookingServiceImpl.java | `ae97170473cf38a8da31ab23cec660f4` | ALL MATCH |
| Booking.java | `a8a69dd3da2c27e8261c6fdaaf06b32e` | ALL MATCH |
| ... (11 files total) | | ALL MATCH |

---

## Quick Start

### 1. Clone repository

```bash
git clone https://github.com/thaitrn/ai-toolkit-demo.git
cd ai-toolkit-demo
```

### 2. Chạy verification script

```bash
./scripts/verify-3-devs.sh
```

### 3. Xem hướng dẫn chi tiết

```bash
cat DEMO_GUIDE.md
```

---

## Cấu trúc Project

```
ai-toolkit-demo/
├── .cursor/rules/           # AI Toolkit rules
├── worktrees/
│   ├── dev1-minh/          # Developer 1 workspace
│   ├── dev2-linh/          # Developer 2 workspace
│   └── dev3-hung/          # Developer 3 workspace
├── scripts/
│   └── verify-3-devs.sh    # Script so sánh code
├── examples/
│   └── verification-result-success.txt
├── DEMO_GUIDE.md           # Hướng dẫn step-by-step
└── README.md               # File này
```

---

## Key Rules Enforced

| Pattern | Forbidden | Required |
|---------|-----------|----------|
| Timestamp | `LocalDateTime` | `Instant` |
| DI | `@Autowired` | `@RequiredArgsConstructor` |
| Pagination | `List<T>` | `Page<T>` |
| Status | `String` | `@Enumerated enum` |
| Update | Manual setters | `mapper.updateEntityFromDto()` |
| Exception | `new NotFoundException()` | `NotFoundException.forEntity()` |

---

## Files Generated (11 files)

1. `Booking.java` - Entity
2. `BookingStatus.java` - Enum
3. `CreateBookingRequestDto.java` - Request DTO
4. `UpdateBookingRequestDto.java` - Update DTO
5. `BookingResponseDto.java` - Response DTO
6. `BookingMapper.java` - MapStruct Mapper
7. `BookingRepository.java` - Repository
8. `BookingService.java` - Service Interface
9. `BookingServiceImpl.java` - Service Implementation
10. `BookingController.java` - REST Controller
11. `NotFoundException.java` - Custom Exception

---

## Demo Prompt

Mở Cursor trong mỗi worktree và chạy prompt:

```
Tạo Booking CRUD service theo rules trong .cursor/rules/
```

---

## Tech Stack

- **Java 17+** / **Spring Boot 3.x**
- **MapStruct** for DTO mapping
- **Lombok** for boilerplate reduction
- **OpenAPI/Swagger** for API documentation
- **Cursor** as AI coding assistant

---

## License

MIT License - Free to use for demo and learning purposes.

---

## Author

Demo created for VTrip Engineering team to demonstrate AI Toolkit capabilities.

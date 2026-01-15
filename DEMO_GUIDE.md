# AI Toolkit Demo - Hướng dẫn Step-by-Step

> Demo này chứng minh: **3 developers khác nhau sử dụng AI (Cursor) để generate cùng một Booking CRUD service, và code được tạo ra 100% IDENTICAL nhờ có AI Toolkit rules.**

---

## Mục tiêu Demo

| Trước khi có Rules | Sau khi có Rules |
|-------------------|------------------|
| Mỗi dev generate code khác nhau | 3 devs generate code IDENTICAL |
| `LocalDateTime` vs `Instant` | Luôn dùng `Instant` |
| `@Autowired` vs `@RequiredArgsConstructor` | Luôn dùng `@RequiredArgsConstructor` |
| `List<T>` vs `Page<T>` | Luôn dùng `Page<T>` |
| Import order khác nhau | Import order chuẩn hóa |

---

## Cấu trúc Project

```
/Users/thaitrn/Projects/ai-toolkit-demo/
├── .cursor/rules/              # AI Toolkit rules
│   ├── 00-strict-standards.mdc # Master rule - định nghĩa patterns
│   ├── 01-core-architecture.mdc
│   ├── 04-mapstruct-mapper.mdc
│   ├── 06-error-handling.mdc
│   ├── 07-api-documentation.mdc
│   ├── 09-code-style.mdc
│   └── templates/              # Generic templates (reference)
├── worktrees/
│   ├── dev1-minh/              # Developer 1 workspace
│   ├── dev2-linh/              # Developer 2 workspace
│   └── dev3-hung/              # Developer 3 workspace
└── scripts/
    └── verify-3-devs.sh        # Script so sánh code 3 devs
```

---

## Hướng dẫn Step-by-Step

### Bước 1: Chuẩn bị môi trường

```bash
cd /Users/thaitrn/Projects/ai-toolkit-demo

# Kiểm tra cấu trúc
ls -la worktrees/
ls -la .cursor/rules/
```

**Kết quả mong đợi:**
- 3 worktrees: dev1-minh, dev2-linh, dev3-hung
- Rules folder có các file .mdc (không có booking-complete/)

---

### Bước 2: Sync rules đến tất cả worktrees

```bash
for dev in dev1-minh dev2-linh dev3-hung; do
  rm -rf "worktrees/$dev/.cursor/rules"
  cp -r .cursor/rules "worktrees/$dev/.cursor/"
  echo "Synced rules to $dev"
done
```

---

### Bước 3: Xóa code cũ (nếu có)

```bash
for dev in dev1-minh dev2-linh dev3-hung; do
  rm -rf "worktrees/$dev/src/main/java/com/vtrip/booking"
  echo "Cleaned $dev"
done
```

---

### Bước 4: Generate code với Cursor

#### 4.1 Developer 1 (Minh)

1. Mở Cursor tại folder: `worktrees/dev1-minh/`
2. Mở chat với AI (Cmd+L hoặc Ctrl+L)
3. Nhập prompt:
   ```
   Tạo Booking CRUD service theo rules trong .cursor/rules/
   ```
4. Chờ AI generate 11 files

#### 4.2 Developer 2 (Linh)

1. Mở Cursor tại folder: `worktrees/dev2-linh/`
2. Nhập cùng prompt:
   ```
   Tạo Booking CRUD service theo rules trong .cursor/rules/
   ```

#### 4.3 Developer 3 (Hùng)

1. Mở Cursor tại folder: `worktrees/dev3-hung/`
2. Nhập cùng prompt:
   ```
   Tạo Booking CRUD service theo rules trong .cursor/rules/
   ```

---

### Bước 5: Chạy script so sánh

```bash
cd /Users/thaitrn/Projects/ai-toolkit-demo
./scripts/verify-3-devs.sh
```

---

### Bước 6: Xem kết quả

Script sẽ hiển thị:
1. **Danh sách files** của mỗi developer
2. **DIFF comparison** - so sánh từng file
3. **MD5 checksums** - hash của từng file
4. **Pattern check** - kiểm tra patterns được áp dụng
5. **Final result** - SUCCESS hoặc DIFFERENCES FOUND

---

## Kết quả mong đợi

```
╔══════════════════════════════════════════════════════════════════════════╗
║   ✅ SUCCESS: All 3 developers have IDENTICAL CODE!                     ║
║                                                                          ║
║   dev1-minh = dev2-linh = dev3-hung                                     ║
║                                                                          ║
║   AI Toolkit rules enforced 100% consistency!                           ║
╚══════════════════════════════════════════════════════════════════════════╝
```

---

## Files được generate (11 files)

| # | File | Package |
|---|------|---------|
| 1 | Booking.java | entity |
| 2 | BookingStatus.java | entity |
| 3 | CreateBookingRequestDto.java | dto/request |
| 4 | UpdateBookingRequestDto.java | dto/request |
| 5 | BookingResponseDto.java | dto/response |
| 6 | BookingMapper.java | mapper |
| 7 | BookingRepository.java | repository |
| 8 | BookingService.java | service |
| 9 | BookingServiceImpl.java | service/impl |
| 10 | BookingController.java | controller |
| 11 | NotFoundException.java | exception |

---

## Rules đảm bảo consistency

| Rule | Mô tả |
|------|-------|
| `00-strict-standards.mdc` | Master rule - định nghĩa tất cả patterns bắt buộc |
| `01-core-architecture.mdc` | Package structure, layered architecture |
| `04-mapstruct-mapper.mdc` | MapStruct configuration, 3 methods bắt buộc |
| `06-error-handling.mdc` | Exception patterns, `NotFoundException.forEntity()` |
| `07-api-documentation.mdc` | OpenAPI annotations bắt buộc |
| `09-code-style.mdc` | Import order, Lombok annotation order |

---

## Key Patterns được enforce

```java
// ✅ CORRECT - Enforced by rules
@RequiredArgsConstructor           // NOT @Autowired
private Instant createdAt;         // NOT LocalDateTime
Page<BookingResponseDto> findAll() // NOT List<T>
@Enumerated(EnumType.STRING)       // NOT String status
mapper.updateEntityFromDto()       // NOT manual setters
NotFoundException.forEntity()      // NOT new NotFoundException()
```

---

## Troubleshooting

### Code không identical?

1. **Kiểm tra rules đã sync:**
   ```bash
   diff -r .cursor/rules worktrees/dev1-minh/.cursor/rules
   ```

2. **Kiểm tra prompt giống nhau:**
   - Đảm bảo cả 3 devs dùng cùng prompt

3. **Clear cache Cursor:**
   - Đóng Cursor, xóa cache, mở lại

### Script không chạy?

```bash
chmod +x scripts/verify-3-devs.sh
./scripts/verify-3-devs.sh
```

---

## Quick Commands

```bash
# Sync rules
for dev in dev1-minh dev2-linh dev3-hung; do
  cp -r .cursor/rules "worktrees/$dev/.cursor/"
done

# Clean all
for dev in dev1-minh dev2-linh dev3-hung; do
  rm -rf "worktrees/$dev/src/main/java/com/vtrip/booking"
done

# Verify
./scripts/verify-3-devs.sh
```

---

## Liên hệ

- Project: `/Users/thaitrn/Projects/ai-toolkit-demo`
- Scripts: `./scripts/verify-3-devs.sh`
- Rules: `.cursor/rules/`

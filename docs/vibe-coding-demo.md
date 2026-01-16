# Vibe Coding: Nâng tầm kỹ năng Engineering

> **Demo cho Team OTA (70+ Engineers)**  
> Ngày: 16/01/2026 | Thời lượng: 15 phút

---

## Slide 1: Mở đầu

# Vibe Coding
## Nâng tầm kỹ năng Engineering với AI

> "Làm việc thông minh hơn, không phải làm việc nhiều hơn"

**Hôm nay chúng ta sẽ:**
- Hiểu Vibe Coding là gì
- Xem demo với Cursor + Rules
- Thảo luận về cách áp dụng

---

## Slide 2: Thách thức hiện tại

# Bạn có thấy quen không?

### Những việc lặp đi lặp lại mỗi ngày:

| Công việc | Thời gian | Giá trị mang lại |
|-----------|-----------|------------------|
| Viết CRUD cho feature mới | 2-3 giờ | Thấp |
| Copy-paste từ file cũ, đổi tên | 30 phút | Rất thấp |
| Nhớ conventions: "Instant hay LocalDate?" | Tra cứu liên tục | Không có |
| Fix review comments về style | 30-60 phút/PR | Thấp |

### Câu hỏi:
> Nếu những việc này được tự động hóa, bạn sẽ dành thời gian cho điều gì?

---

## Slide 3: Cơ hội phát triển

# Tập trung vào điều quan trọng

### Thay vì viết boilerplate, bạn có thể:

```
┌─────────────────────────────────────────────────────────┐
│                                                         │
│   Trước đây              →        Bây giờ               │
│                                                         │
│   Viết CRUD lặp lại      →   Thiết kế Architecture      │
│   Copy-paste code        →   Giải quyết business logic  │
│   Tra cứu conventions    →   Code review sâu về logic   │
│   Fix style comments     →   Học patterns mới           │
│                                                         │
│         Công việc có giá trị cao = Growth nhanh hơn     │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

### Kết quả:
- **Skill tăng**: Tập trung vào problem-solving, không phải syntax
- **Impact lớn hơn**: Deliver features nhanh, chất lượng cao
- **Career growth**: Thời gian học công nghệ mới, design patterns

---

## Slide 4: AI = Junior Dev của bạn

# AI như một Junior Developer hỗ trợ bạn

### Cách làm việc mới:

```
┌──────────────────────────────────────────────────────────┐
│                                                          │
│    BẠN (Senior/Lead)              AI (Junior Dev)        │
│         │                              │                 │
│         │─── Mô tả yêu cầu ───────────>│                 │
│         │    "Tạo API exchange rate"   │                 │
│         │                              │                 │
│         │<── Draft code ───────────────│                 │
│         │    (7 files theo chuẩn)      │                 │
│         │                              │                 │
│         │─── Review & Feedback ───────>│                 │
│         │    "Thêm validation này"     │                 │
│         │                              │                 │
│         │<── Code hoàn chỉnh ──────────│                 │
│         │                              │                 │
│    ✓ BẠN vẫn là người quyết định cuối cùng               │
│                                                          │
└──────────────────────────────────────────────────────────┘
```

### Key point:
- AI viết draft → Bạn review và hoàn thiện
- Bạn vẫn nắm quyền kiểm soát 100%
- AI không thay thế, AI hỗ trợ

---

## Slide 5: Cursor Rules = Team Knowledge

# Cursor Rules: Kiến thức team được mã hóa

### Project VTrip CMS đã có sẵn 15 rule files:

```
.cursor/rules/
├── 00-core-standards.mdc      # Conventions bắt buộc
├── 00-feature-generation.mdc  # Checklist tạo feature
├── 01-entity-layer.mdc        # Entity patterns
├── 02-dto-layer.mdc           # DTO patterns
├── 03-repository-layer.mdc    # Repository patterns
├── 04-service-layer.mdc       # Service layer rules
├── 05-controller-layer.mdc    # Controller conventions
├── 06-integration-layer.mdc   # External API integration
├── 07-scheduler-layer.mdc     # Scheduled tasks
├── 08-exception-layer.mdc     # Exception handling
├── 09-mapper-layer.mdc        # MapStruct mappers
├── 10-test-layer.mdc          # Testing patterns
├── 11-cache-helper-layer.mdc  # Caching patterns
└── templates/                 # 12 code templates
```

### Lợi ích:
- **Consistency**: Mọi người generate code giống nhau
- **Onboarding nhanh**: Dev mới follow rules ngay từ đầu
- **Kiến thức không mất**: Best practices được lưu lại

---

## Slide 6: Demo - Core Standards

# Ví dụ: Conventions được mã hóa

### Từ file `00-core-standards.mdc`:

**Kiểu dữ liệu bắt buộc:**

| Loại | KHÔNG DÙNG | BẮT BUỘC DÙNG |
|------|------------|---------------|
| Thời gian | `LocalDateTime`, `LocalDate` | `Instant` |
| ID | `Long`, `Integer` | `UUID` |
| Tiền/Tỷ giá | `Double`, `Float` | `BigDecimal` |

**Dependency Injection:**

```java
// KHÔNG DÙNG
@Autowired
private MyService myService;

// BẮT BUỘC DÙNG
@RequiredArgsConstructor
public class MyClass {
    private final MyService myService;
}
```

**Đặt tên:**

| Layer | KHÔNG DÙNG | BẮT BUỘC |
|-------|------------|----------|
| Repository | `BookingRepository` | `IBookingRepository` |
| Service Interface | Không có | `IBookingService` |
| Service Impl | `BookingServiceImpl` | `BookingService` |
| Mapper | `BookingConverter` | `BookingMapper` |

→ **AI đọc rules này và generate đúng 100% conventions**

---

## Slide 7: Demo - Feature Generation

# Checklist: Tạo feature mới tự động

### Từ file `00-feature-generation.mdc`:

Khi tạo một feature mới, AI sẽ tự động generate đúng **7 files bắt buộc**:

| # | Layer | File Path |
|---|-------|-----------|
| 1 | Entity | `features/{feature}/model/{Name}.java` |
| 2 | Repository | `features/{feature}/repository/I{Name}Repository.java` |
| 3 | Service Interface | `features/{feature}/service/I{Name}Service.java` |
| 4 | Service Impl | `features/{feature}/service/{Name}Service.java` |
| 5 | Response DTO | `features/{feature}/dto/{Name}Response.java` |
| 6 | Mapper | `features/{feature}/dto/{Name}Mapper.java` |
| 7 | Controller | `features/{feature}/controller/{Name}Controller.java` |

### Kết quả:
```
Prompt: "Tạo feature ExchangeRate với API lấy tỷ giá theo ngày"

→ AI tự động tạo 7 files đúng cấu trúc, đúng conventions
→ Thời gian: 2 phút thay vì 2-3 giờ
```

---

## Slide 8: Demo - Code thực tế

# Ví dụ: Entity được generate

### Prompt:
> "Tạo entity ExchangeRate với các field: processDate, baseCurrency, targetCurrency, buyRate, transferRate, sellRate"

### Kết quả (AI generate theo rules):

```java
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "m_exchange_rate", schema = "splatform_meta")
public class ExchangeRate extends BaseEntity {
    
    @Column(name = "process_date", nullable = false)
    private Instant processDate;              // ✓ Dùng Instant

    @Column(name = "base_currency", nullable = false, length = 3)
    private String baseCurrency;

    @Column(name = "target_currency", nullable = false, length = 3)
    private String targetCurrency;

    @Column(name = "buy_rate", nullable = false, precision = 18, scale = 6)
    private BigDecimal buyRate;               // ✓ Dùng BigDecimal

    @Column(name = "transfer_rate", nullable = false, precision = 18, scale = 6)
    private BigDecimal transferRate;

    @Column(name = "sell_rate", nullable = false, precision = 18, scale = 6)
    private BigDecimal sellRate;
}
```

**Tất cả conventions đều đúng tự động!**

---

## Slide 9: Review chéo = Học hỏi lẫn nhau

# Peer Review: Win-Win cho cả hai bên

### Workflow mới:

```
┌─────────────────────────────────────────────────────────┐
│                                                         │
│    Developer A                         Developer B      │
│        │                                    │           │
│        │──── Prompt + Generate ────>        │           │
│        │                                    │           │
│        │──── Self-review ────>              │           │
│        │                                    │           │
│        │════════ Tạo Pull Request ═════════>│           │
│        │                                    │           │
│        │<═══════ Review & Feedback ═════════│           │
│        │                                    │           │
│        │         Cả hai đều học hỏi         │           │
│        │                                    │           │
└─────────────────────────────────────────────────────────┘
```

### Người viết code học được:
- Nhận feedback cải thiện skill
- Hiểu cách AI generate code
- Best practices từ reviewer

### Người review học được:
- Tiếp xúc nhiều phần codebase
- Học patterns từ người khác
- Nâng cao khả năng đọc code nhanh

---

## Slide 10: Review Checklist

# Checklist Review khi dùng AI-generated code

### Focus vào logic, không phải style:

```
┌─────────────────────────────────────────────────────────┐
│           PEER REVIEW CHECKLIST                         │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  □ Business Logic                                       │
│    └─ Logic nghiệp vụ có đúng requirement không?        │
│                                                         │
│  □ Edge Cases                                           │
│    └─ Xử lý null, empty, boundary conditions?           │
│                                                         │
│  □ Security                                             │
│    └─ Input validation, SQL injection, auth?            │
│                                                         │
│  □ Performance                                          │
│    └─ N+1 queries, unnecessary loops?                   │
│                                                         │
│  □ Test Coverage                                        │
│    └─ Unit tests cover happy path + edge cases?         │
│                                                         │
│  ────────────────────────────────────────────────────   │
│  KHÔNG CẦN CHECK (AI đã xử lý theo Rules):              │
│  ✓ Naming conventions                                   │
│  ✓ Code style                                           │
│  ✓ Import order                                         │
│  ✓ Annotation order                                     │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

→ **Review time giảm 50-70%** khi không cần check style

---

## Slide 11: Lợi ích cá nhân

# Bạn được gì từ Vibe Coding?

### So sánh trước và sau:

| Metric | Trước | Sau |
|--------|-------|-----|
| **Thời gian viết boilerplate** | 2-3 giờ/feature | 10-15 phút |
| **Review comments về style** | 5-10/PR | ~0 |
| **Số lần tra cứu conventions** | Liên tục | Không cần |
| **Thời gian cho logic phức tạp** | Ít | Nhiều hơn |

### Kết quả cho career:

```
┌─────────────────────────────────────────────────────────┐
│                                                         │
│   Ít thời gian cho việc lặp lại                         │
│              ↓                                          │
│   Nhiều thời gian cho việc có giá trị cao               │
│              ↓                                          │
│   Skill tăng nhanh hơn                                  │
│              ↓                                          │
│   Impact lớn hơn                                        │
│              ↓                                          │
│   Career growth                                         │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

---

## Slide 12: Bắt đầu như thế nào?

# Roadmap cá nhân

### Tuần 1-2: Làm quen
- Cài đặt Cursor Pro
- Đọc qua `.cursor/rules/` hiện có
- Thử generate 1 feature đơn giản

### Tuần 3-4: Áp dụng
- Dùng cho tasks hàng ngày
- Tham gia review code AI-generated của đồng nghiệp
- Đóng góp cải thiện Rules

### Sau đó: Thành thạo
- Viết Rules mới cho patterns team hay dùng
- Chia sẻ tips với team
- Mentor người mới

### Workshop tiếp theo:
> Hands-on session nhỏ (10 người/nhóm, 2 giờ)
> Đăng ký: [Link đăng ký]

---

## Slide 13: FAQ

# Câu hỏi thường gặp

### "AI generate code có bug thì sao?"
> Đó là lý do cần peer review. AI là junior dev, vẫn cần senior review.
> Workflow: AI draft → Self review → Peer review → Merge

### "Có mất việc không?"
> Không. AI giúp bạn làm nhanh hơn, không thay thế bạn.
> Những việc cần human: business logic, architecture, decision making

### "Code có gửi ra ngoài không?"
> Cursor Pro có option chạy local. Kiểm tra settings Privacy Mode.

### "Tôi không quen prompt thì sao?"
> Bắt đầu đơn giản: "Tạo entity X với fields A, B, C"
> Workshop sẽ hướng dẫn chi tiết

### "Rules cũ không đúng thì sao?"
> Mọi người đều có thể đóng góp cải thiện rules
> Tạo PR vào `.cursor/rules/`

---

## Slide 14: Kết thúc

# Tóm tắt

```
┌─────────────────────────────────────────────────────────┐
│                                                         │
│              VIBE CODING = WIN-WIN                      │
│                                                         │
│   ┌─────────────────────────────────────────────────┐   │
│   │                                                 │   │
│   │   AI xử lý      +      Bạn tập trung            │   │
│   │   boilerplate          logic phức tạp           │   │
│   │                                                 │   │
│   └─────────────────────────────────────────────────┘   │
│                          +                              │
│   ┌─────────────────────────────────────────────────┐   │
│   │                                                 │   │
│   │          Peer Review = Học hỏi lẫn nhau         │   │
│   │                                                 │   │
│   └─────────────────────────────────────────────────┘   │
│                          =                              │
│   ┌─────────────────────────────────────────────────┐   │
│   │                                                 │   │
│   │      Skill Growth + Quality Code + Speed        │   │
│   │                                                 │   │
│   └─────────────────────────────────────────────────┘   │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

### Next steps:
1. Thử Cursor Pro ngay hôm nay
2. Đọc `.cursor/rules/` trong project
3. Đăng ký workshop hands-on

---

## Slide 15: Q&A

# Hỏi & Đáp

> Thời gian còn lại dành cho câu hỏi từ team

**Contacts:**
- Workshop đăng ký: [Link]
- Slack channel: #vibe-coding
- Rules contribution: `.cursor/rules/` trong repo

---

*Cảm ơn các bạn đã tham dự!*

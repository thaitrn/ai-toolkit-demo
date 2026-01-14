# AI Toolkit - Hướng dẫn sử dụng

## Mục lục
1. [Giới thiệu](#giới-thiệu)
2. [Cấu trúc Toolkit](#cấu-trúc-toolkit)
3. [Project Rules](#project-rules)
4. [Team Rules](#team-rules)
5. [Workflows](#workflows)
6. [Cách sử dụng](#cách-sử-dụng)
7. [Best Practices](#best-practices)
8. [FAQ](#faq)

---

## Giới thiệu

AI Toolkit này được thiết kế để giúp team engineer làm việc hiệu quả hơn với AI coding assistants (Cursor, GitHub Copilot, etc.) thông qua hệ thống rules và workflows chuẩn hóa.

### Lợi ích:
- ✅ **Consistency** - Code tuân thủ chuẩn của team
- ✅ **Productivity** - Giảm thời gian review, sửa lỗi
- ✅ **Onboarding** - Team members mới dễ dàng tiếp cận
- ✅ **Scalability** - Dễ mở rộng cho nhiều projects

---

## Cấu trúc Toolkit

```
project/
├── .cursor/
│   └── rules/                    # Project-level AI rules
│       ├── 01-core-architecture.mdc
│       ├── 02-code-review.mdc
│       ├── 03-test-generation.mdc
│       ├── 04-mapstruct-mapper.mdc
│       ├── 05-feign-client.mdc
│       ├── 06-error-handling.mdc
│       ├── 07-api-documentation.mdc
│       ├── 08-kafka-events.mdc
│       └── templates/            # Code templates
├── .agent/
│   └── workflows/                # Automation workflows
│       ├── code-review.md
│       ├── pr-analysis.md
│       └── new-service.md
├── AGENTS.md                     # Quick-start AI instructions
└── docs/
    ├── team-rules-templates.md   # Templates cho Team Rules
    └── README.md                 # This file
```

---

## Project Rules

### Loại Rules và cách kích hoạt:

| Rule | Type | Kích hoạt |
|------|------|-----------|
| `01-core-architecture` | Always Apply | Tự động mọi chat |
| `02-code-review` | Manual | Gọi `@code-review` |
| `03-test-generation` | File Pattern | Khi edit `*Test.java` |
| `04-mapstruct-mapper` | Intelligent | AI tự quyết định |
| `05-feign-client` | Intelligent | AI tự quyết định |
| `06-error-handling` | Always Apply | Tự động mọi chat |
| `07-api-documentation` | File Pattern | Khi edit `*Controller.java` |
| `08-kafka-events` | Intelligent | AI tự quyết định |

### Cách tạo rule mới:

1. Tạo file `.mdc` trong `.cursor/rules/`
2. Thêm frontmatter:
```yaml
---
description: Mô tả rule (cho Intelligent type)
globs: patterns (cho File Pattern type)
alwaysApply: true/false
---
```
3. Viết nội dung rule (Markdown)

---

## Team Rules

Team Rules được quản lý tập trung trên Cursor Dashboard.

### Cách setup:
1. Login [Cursor Dashboard](https://cursor.com/dashboard)
2. Navigate to **Team Content** tab
3. Click **Add Rule**
4. Copy nội dung từ `docs/team-rules-templates.md`
5. Chọn options:
   - **Enable immediately**: Kích hoạt ngay
   - **Enforce**: Bắt buộc (không thể tắt)

### Recommended Team Rules:
- ✅ Company Coding Standards (Enforce)
- ✅ Security Guidelines (Enforce)
- ⚪ Git Workflow (Optional)
- ⚪ Review Checklist (Optional)

---

## Workflows

### Sử dụng:

```
/code-review      # Review code theo checklist
/pr-analysis      # Phân tích GitHub PR
/new-service      # Scaffold microservice mới
```

### Tạo workflow mới:
1. Tạo file trong `.agent/workflows/`
2. Format:
```yaml
---
description: Mô tả workflow
---

# Workflow Name

## Steps
1. Step 1
2. Step 2
...
```

---

## Cách sử dụng

### Daily Development:
```
1. Mở project trong Cursor
2. Rules tự động được load
3. Viết code như bình thường
4. AI sẽ follow rules của team
```

### Code Review:
```
1. Trong chat, type: @code-review
2. Chọn files cần review
3. AI sẽ review theo checklist
```

### Tạo Service mới:
```
1. Trong chat, run: /new-service
2. Trả lời các câu hỏi
3. AI sẽ scaffold full service
```

---

## Best Practices

### ✅ Nên làm:
- Giữ mỗi rule < 500 dòng
- Reference files thay vì copy content
- Update rules khi thấy AI mắc lỗi lặp lại
- Commit rules vào Git
- Review và iterate rules thường xuyên

### ❌ Không nên:
- Copy toàn bộ style guide vào rules
- Tạo quá nhiều rules (< 15 rules là đủ)
- Hardcode examples sẽ outdated
- Bỏ qua Team Rules enforcement

---

## FAQ

### Q: Rules có apply cho tất cả AI tools không?
A: Hiện tại chỉ Cursor hỗ trợ native. Nhưng AGENTS.md có thể được đọc bởi các tools khác.

### Q: Làm sao sync rules giữa các repos?
A: Sử dụng Remote Rules (GitHub) để import từ central repository.

### Q: Team Rule vs Project Rule?
A: Team Rules cho company-wide standards (security, git workflow). Project Rules cho domain-specific patterns.

### Q: Có thể tắt enforced Team Rules không?
A: Không. Đó là mục đích của "Enforce" - đảm bảo compliance.

### Q: Rules ảnh hưởng performance không?
A: Minimal. Rules được inject vào context, không ảnh hưởng response time đáng kể.

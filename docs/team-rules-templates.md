# Team Rules Templates

> Các rule này được quản lý trên **Cursor Dashboard** cho toàn team.
> Chỉ áp dụng cho gói **Team** hoặc **Enterprise**.

---

## 1. Company Coding Standards (ENFORCE: Yes)

```
# VTrip Coding Standards

## Bắt buộc tuân thủ:

### 1. Code Quality
- Không commit code với TODO/FIXME chưa có ticket
- Không có magic numbers - sử dụng constants
- Tất cả public methods phải có Javadoc
- Code coverage > 80% cho new code

### 2. Security
- KHÔNG hardcode secrets, passwords, API keys
- Sử dụng environment variables hoặc AWS Secrets Manager
- Validate ALL user inputs
- Sử dụng parameterized queries (không string concatenation)

### 3. Git Workflow
- Branch naming: feature/{ticket-id}-short-description
- Commit message: <type>(<scope>): <description>
  - Types: feat, fix, docs, refactor, test, chore
- Squash commits before merge
- Phải có code review trước khi merge

### 4. API Standards
- Versioning: /api/v1/...
- JSON response format chuẩn
- HTTP status codes đúng ngữ nghĩa
- OpenAPI/Swagger documentation bắt buộc
```

---

## 2. Security Guidelines (ENFORCE: Yes)

```
# Security Guidelines

## Authentication & Authorization
- Sử dụng JWT tokens cho authentication
- Token expiry: Access 15 mins, Refresh 7 days
- Implement proper authorization checks ở service layer

## Input Validation
- Validate tất cả request parameters
- Sanitize input trước khi log
- Không log sensitive data (passwords, tokens, PII)

## Data Protection
- Encrypt sensitive data at rest
- Use TLS 1.2+ for all connections
- PII data phải được mask khi log

## Dependencies
- Scan dependencies cho vulnerabilities
- Update dependencies thường xuyên
- Không sử dụng deprecated libraries
```

---

## 3. Git Workflow (ENFORCE: No)

```
# Git Workflow Guidelines

## Branch Strategy
- main: production-ready code
- develop: integration branch
- feature/*: new features
- bugfix/*: bug fixes
- hotfix/*: production hotfixes

## Commit Message Format
<type>(<scope>): <description>

[optional body]

[optional footer]

Types:
- feat: new feature
- fix: bug fix
- docs: documentation
- refactor: code refactoring
- test: adding tests
- chore: maintenance

## Pull Request Checklist
- [ ] Tests pass
- [ ] Code coverage maintained
- [ ] Documentation updated
- [ ] No console.log or debug code
- [ ] Approved by at least 1 reviewer
```

---

## 4. Review Checklist (ENFORCE: No)

```
# Code Review Checklist

## Khi review code, kiểm tra:

### Functionality
- [ ] Code hoạt động đúng theo requirements
- [ ] Edge cases được handle
- [ ] Error handling đầy đủ

### Code Quality
- [ ] Follows naming conventions
- [ ] DRY - không duplicate code
- [ ] SOLID principles
- [ ] Methods ngắn gọn, single responsibility

### Performance
- [ ] No N+1 queries
- [ ] Proper indexing
- [ ] Caching considerations

### Security
- [ ] Input validation
- [ ] No hardcoded secrets
- [ ] Proper authorization

### Testing
- [ ] Unit tests cho business logic
- [ ] Integration tests cho APIs
- [ ] Edge cases covered

### Documentation
- [ ] Public APIs documented
- [ ] Complex logic explained
- [ ] README updated if needed
```

---

## Cách apply Team Rules:

1. Truy cập [Cursor Dashboard](https://cursor.com/dashboard?tab=team-content)
2. Click "Add Rule"
3. Paste nội dung rule
4. Chọn "Enable immediately" và "Enforce" nếu cần
5. Save

> **Note:** Team Rules là plain text, không hỗ trợ frontmatter như Project Rules.

# 🚀 Quick Test Guide: Git Worktrees (5 Developers trong 10 phút)

## Tổng quan

Sử dụng **Git Worktrees** để tạo 5 working directories riêng biệt, mỗi directory đại diện cho 1 developer. Cho phép test song song trong 1 máy thay vì cần 5 machines.

---

## 📋 Flow Diagram

```
┌─────────────────────────────────────────────────────────────────────────┐
│                           MAIN REPOSITORY                                │
│                    (với AI Toolkit .cursor/rules/)                       │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
           ┌────────────────────────┼────────────────────────┐
           │                        │                        │
           ▼                        ▼                        ▼
    ┌─────────────┐          ┌─────────────┐          ┌─────────────┐
    │  worktree/  │          │  worktree/  │          │  worktree/  │
    │  dev1-minh  │          │  dev2-linh  │          │  dev3-hung  │
    │  (Senior)   │          │  (Junior)   │          │ (Ex-NodeJS) │
    └─────────────┘          └─────────────┘          └─────────────┘
           │                        │                        │
           │   cursor prompt        │   cursor prompt        │   cursor prompt
           ▼                        ▼                        ▼
    ┌─────────────┐          ┌─────────────┐          ┌─────────────┐
    │ BookingSvc  │    =     │ BookingSvc  │    =     │ BookingSvc  │
    │  (code A)   │          │  (code A)   │          │  (code A)   │
    └─────────────┘          └─────────────┘          └─────────────┘
                                    │
                         ┌──────────┴──────────┐
                         ▼                     ▼
                   ┌───────────┐         ┌───────────┐
                   │   diff    │         │    md5    │
                   │ comparison│         │ checksum  │
                   └───────────┘         └───────────┘
                         │                     │
                         └──────────┬──────────┘
                                    ▼
                         ┌───────────────────────┐
                         │ ✅ 100% IDENTICAL!   │
                         └───────────────────────┘
```

---

## 🛠️ Step 1: Setup Worktrees (1 phút)

```bash
cd /path/to/your/project  # Project có .cursor/rules/

# Chạy setup script
./scripts/setup-worktrees.sh
```

**Output:**
```
✓ Creating worktree: dev1-minh (Senior Spring Boot - 3 năm exp)
✓ Creating worktree: dev2-linh (Junior - 1 năm exp)
✓ Creating worktree: dev3-hung (Ex-NodeJS developer)
✓ Creating worktree: dev4-trang (Senior mới join team)
✓ Creating worktree: dev5-nam (Mid-level - 2 năm exp)
```

---

## 🖥️ Step 2: Open 5 Cursor Windows (1 phút)

Mở 5 terminal tabs và chạy:

```bash
# Terminal 1 - Dev Minh (Senior)
cursor worktrees/dev1-minh

# Terminal 2 - Dev Linh (Junior)
cursor worktrees/dev2-linh

# Terminal 3 - Dev Hung (Ex-NodeJS)
cursor worktrees/dev3-hung

# Terminal 4 - Dev Trang (DDD expert)
cursor worktrees/dev4-trang

# Terminal 5 - Dev Nam (Mid-level)
cursor worktrees/dev5-nam
```

---

## 💬 Step 3: Prompt AI (5 phút)

Mỗi Cursor window, mở Chat (Cmd+L) và paste prompt tương ứng:

### Dev 1 - Minh (Senior):
```
Tạo BookingService với CRUD operations. Dùng constructor injection,
MapStruct cho mapping, và proper exception handling.
```

### Dev 2 - Linh (Junior):
```
Làm ơn tạo service cho Booking entity với các method:
get, create, update, delete
```

### Dev 3 - Hung (Ex-NodeJS):
```
Create BookingService with CRUD operations.
Use proper error handling and modern patterns.
```

### Dev 4 - Trang (DDD style):
```
Implement BookingService following repository pattern.
Include proper domain exceptions.
```

### Dev 5 - Nam (Mid-level):
```
Viết BookingService với repository và xử lý exception khi không tìm thấy
```

---

## 💾 Step 4: Save Code (2 phút)

Trong mỗi Cursor window, lưu file AI generate vào:

```
src/main/java/com/vtrip/booking/service/impl/BookingServiceImpl.java
```

Sau đó commit trong mỗi worktree:
```bash
git add .
git commit -m "feat: add BookingService"
```

---

## ✅ Step 5: Verify (1 phút)

Quay lại terminal chính:

```bash
./scripts/verify-worktrees.sh
```

**Expected Output:**
```
═══════════════════════════════════════════════════════════════════════════
[2/4] DIFF COMPARISON
═══════════════════════════════════════════════════════════════════════════

  ✓ dev1-minh vs dev2-linh: IDENTICAL
  ✓ dev1-minh vs dev3-hung: IDENTICAL
  ✓ dev1-minh vs dev4-trang: IDENTICAL
  ✓ dev1-minh vs dev5-nam: IDENTICAL

═══════════════════════════════════════════════════════════════════════════
[3/4] MD5 CHECKSUMS
═══════════════════════════════════════════════════════════════════════════

  dev1-minh: a1b2c3d4e5f6g7h8i9j0...
  dev2-linh: a1b2c3d4e5f6g7h8i9j0...
  dev3-hung: a1b2c3d4e5f6g7h8i9j0...
  dev4-trang: a1b2c3d4e5f6g7h8i9j0...
  dev5-nam: a1b2c3d4e5f6g7h8i9j0...

╔══════════════════════════════════════════════════════════════════════════╗
║   ✅ SUCCESS: ALL 5 DEVELOPERS PRODUCED IDENTICAL CODE!                ║
╚══════════════════════════════════════════════════════════════════════════╝
```

---

## 📊 Metrics Summary

| Step | Time | Action |
|------|------|--------|
| Setup | 1 min | `./scripts/setup-worktrees.sh` |
| Open Cursor | 1 min | 5x `cursor worktrees/dev-*` |
| Prompt AI | 5 min | 5 different prompts |
| Save & Commit | 2 min | Save files, git commit |
| Verify | 1 min | `./scripts/verify-worktrees.sh` |
| **Total** | **~10 min** | |

---

## 🧹 Cleanup

Sau khi test xong:
```bash
# Remove all worktrees
rm -rf worktrees/
git worktree prune
git branch -D dev1-minh dev2-linh dev3-hung dev4-trang dev5-nam
```

---

## 🔑 Key Commands Summary

```bash
# 1. Setup
./scripts/setup-worktrees.sh

# 2. Open Cursor (5 windows)
cursor worktrees/dev1-minh
cursor worktrees/dev2-linh
cursor worktrees/dev3-hung
cursor worktrees/dev4-trang
cursor worktrees/dev5-nam

# 3. After all devs commit, verify
./scripts/verify-worktrees.sh

# 4. Cleanup
rm -rf worktrees/ && git worktree prune
```

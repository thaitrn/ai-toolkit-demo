#!/bin/bash

# =============================================================================
# AI TOOLKIT: Quick Demo với Git Worktrees
# =============================================================================
# Compatible with bash 3.x (macOS default) and zsh
# =============================================================================

set -e

echo "╔══════════════════════════════════════════════════════════════════════════╗"
echo "║  AI TOOLKIT: Quick Demo với Git Worktrees (5 Developers)                ║"
echo "╚══════════════════════════════════════════════════════════════════════════╝"
echo ""

# Colors
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
CYAN='\033[0;36m'
NC='\033[0m'

DEMO_DIR=$(pwd)
WORKTREES_DIR="$DEMO_DIR/worktrees"

# =============================================================================
# STEP 1: SETUP WORKTREES
# =============================================================================

echo -e "${BLUE}STEP 1:${NC} Creating 5 worktrees (simulating 5 developers)"
echo "═══════════════════════════════════════════════════════════════════════════"
echo ""

# Cleanup
rm -rf "$WORKTREES_DIR"
git worktree prune 2>/dev/null || true
mkdir -p "$WORKTREES_DIR"

# Developer list (compatible with bash 3.x)
DEVS="dev1-minh dev2-linh dev3-hung dev4-trang dev5-nam"

# Create worktrees
for dev in $DEVS; do
    case $dev in
        dev1-minh) desc="Senior Spring Boot - 3 năm exp" ;;
        dev2-linh) desc="Junior - 1 năm exp" ;;
        dev3-hung) desc="Ex-NodeJS developer" ;;
        dev4-trang) desc="Senior mới join team" ;;
        dev5-nam) desc="Mid-level - 2 năm exp" ;;
    esac
    
    echo -e "  ${GREEN}✓${NC} Creating worktree: $dev ($desc)"
    git branch -D "$dev" 2>/dev/null || true
    git worktree add "$WORKTREES_DIR/$dev" -b "$dev" HEAD --quiet
done

echo ""
echo -e "${GREEN}Worktrees created:${NC}"
git worktree list
echo ""

# =============================================================================
# STEP 2: INSTRUCTIONS
# =============================================================================

echo "═══════════════════════════════════════════════════════════════════════════"
echo -e "${BLUE}STEP 2:${NC} Open each worktree in Cursor and generate BookingService"
echo "═══════════════════════════════════════════════════════════════════════════"
echo ""

echo -e "${CYAN}Mở 5 terminal windows và chạy từng lệnh sau:${NC}"
echo ""

for dev in $DEVS; do
    echo -e "${YELLOW}Terminal $dev:${NC}"
    echo "  cursor $WORKTREES_DIR/$dev"
    echo ""
done

echo "═══════════════════════════════════════════════════════════════════════════"
echo -e "${BLUE}STEP 3:${NC} Prompts cho từng developer (copy vào Cursor Chat)"
echo "═══════════════════════════════════════════════════════════════════════════"
echo ""

cat << 'PROMPTS'
┌──────────────────────────────────────────────────────────────────────────────┐
│ DEV1-MINH (Senior):                                                          │
│ "Tạo BookingService với CRUD, constructor injection, MapStruct mapping"     │
├──────────────────────────────────────────────────────────────────────────────┤
│ DEV2-LINH (Junior):                                                          │
│ "Làm ơn tạo service cho Booking với get, create, update, delete"            │
├──────────────────────────────────────────────────────────────────────────────┤
│ DEV3-HUNG (Ex-NodeJS):                                                       │
│ "Create BookingService with CRUD operations and proper error handling"       │
├──────────────────────────────────────────────────────────────────────────────┤
│ DEV4-TRANG (DDD style):                                                      │
│ "Implement BookingService following repository pattern with exceptions"      │
├──────────────────────────────────────────────────────────────────────────────┤
│ DEV5-NAM (Mid-level):                                                        │
│ "Viết BookingService với repository và xử lý exception not found"           │
└──────────────────────────────────────────────────────────────────────────────┘
PROMPTS

echo ""
echo "═══════════════════════════════════════════════════════════════════════════"
echo -e "${BLUE}STEP 4:${NC} Lưu code vào đúng path sau khi AI generate"
echo "═══════════════════════════════════════════════════════════════════════════"
echo ""

echo "Trong mỗi worktree, lưu file vào:"
echo ""
echo "  src/main/java/com/vtrip/booking/service/impl/BookingServiceImpl.java"
echo ""
echo "Sau đó commit trong mỗi worktree terminal:"
echo ""
echo '  git add . && git commit -m "feat: add BookingService"'
echo ""

echo "═══════════════════════════════════════════════════════════════════════════"
echo -e "${BLUE}STEP 5:${NC} Sau khi TẤT CẢ 5 dev đã commit, chạy verify script"
echo "═══════════════════════════════════════════════════════════════════════════"
echo ""
echo "  ./scripts/verify-worktrees.sh"
echo ""
echo "═══════════════════════════════════════════════════════════════════════════"

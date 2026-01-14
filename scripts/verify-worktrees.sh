#!/bin/bash

# =============================================================================
# VERIFY: Compare code from all 5 worktrees
# =============================================================================

set -e

echo "╔══════════════════════════════════════════════════════════════════════════╗"
echo "║  VERIFICATION: Comparing BookingService from 5 Developers               ║"
echo "╚══════════════════════════════════════════════════════════════════════════╝"
echo ""

# Colors
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

DEMO_DIR=$(pwd)
WORKTREES_DIR="$DEMO_DIR/worktrees"
SERVICE_PATH="src/main/java/com/vtrip/booking/service/impl/BookingServiceImpl.java"

DEVELOPERS=("dev1-minh" "dev2-linh" "dev3-hung" "dev4-trang" "dev5-nam")

# Check worktrees exist
if [ ! -d "$WORKTREES_DIR" ]; then
    echo -e "${RED}Error: Worktrees directory not found. Run setup-worktrees.sh first.${NC}"
    exit 1
fi

echo -e "${BLUE}[1/4] Checking files exist...${NC}"
echo ""

MISSING=0
for dev in "${DEVELOPERS[@]}"; do
    FILE="$WORKTREES_DIR/$dev/$SERVICE_PATH"
    if [ -f "$FILE" ]; then
        echo -e "  ${GREEN}✓${NC} $dev: Found"
    else
        echo -e "  ${RED}✗${NC} $dev: MISSING - $SERVICE_PATH"
        MISSING=1
    fi
done

if [ "$MISSING" -eq 1 ]; then
    echo ""
    echo -e "${RED}Some files missing. Ensure all developers have saved their code.${NC}"
    exit 1
fi

echo ""
echo "═══════════════════════════════════════════════════════════════════════════"
echo -e "${BLUE}[2/4] DIFF COMPARISON${NC}"
echo "═══════════════════════════════════════════════════════════════════════════"
echo ""

FIRST="${DEVELOPERS[0]}"
FIRST_FILE="$WORKTREES_DIR/$FIRST/$SERVICE_PATH"
ALL_IDENTICAL=true

for dev in "${DEVELOPERS[@]:1}"; do
    DEV_FILE="$WORKTREES_DIR/$dev/$SERVICE_PATH"
    
    if diff -q "$FIRST_FILE" "$DEV_FILE" > /dev/null 2>&1; then
        echo -e "  ${GREEN}✓${NC} $FIRST vs $dev: ${GREEN}IDENTICAL${NC}"
    else
        echo -e "  ${RED}✗${NC} $FIRST vs $dev: ${RED}DIFFERENT${NC}"
        ALL_IDENTICAL=false
    fi
done

echo ""
echo "═══════════════════════════════════════════════════════════════════════════"
echo -e "${BLUE}[3/4] MD5 CHECKSUMS${NC}"
echo "═══════════════════════════════════════════════════════════════════════════"
echo ""

for dev in "${DEVELOPERS[@]}"; do
    FILE="$WORKTREES_DIR/$dev/$SERVICE_PATH"
    HASH=$(md5 -q "$FILE")
    echo "  $dev: $HASH"
done

echo ""
echo "═══════════════════════════════════════════════════════════════════════════"
echo -e "${BLUE}[4/4] PATTERN CHECK${NC}"
echo "═══════════════════════════════════════════════════════════════════════════"
echo ""

echo -e "${YELLOW}DI Pattern:${NC}"
for dev in "${DEVELOPERS[@]}"; do
    FILE="$WORKTREES_DIR/$dev/$SERVICE_PATH"
    PATTERN=$(grep -o "@RequiredArgsConstructor\|@Autowired" "$FILE" | head -1)
    echo "  $dev: $PATTERN"
done

echo ""
echo -e "${YELLOW}Exception Type:${NC}"
for dev in "${DEVELOPERS[@]}"; do
    FILE="$WORKTREES_DIR/$dev/$SERVICE_PATH"
    EX=$(grep -o "NotFoundException\|RuntimeException\|Exception" "$FILE" | head -1)
    echo "  $dev: $EX"
done

echo ""
echo "═══════════════════════════════════════════════════════════════════════════"
echo -e "${BLUE}FINAL RESULT${NC}"
echo "═══════════════════════════════════════════════════════════════════════════"
echo ""

if [ "$ALL_IDENTICAL" = true ]; then
    echo -e "${GREEN}╔══════════════════════════════════════════════════════════════════════════╗${NC}"
    echo -e "${GREEN}║                                                                          ║${NC}"
    echo -e "${GREEN}║   ✅ SUCCESS: ALL 5 DEVELOPERS PRODUCED IDENTICAL CODE!                ║${NC}"
    echo -e "${GREEN}║                                                                          ║${NC}"
    echo -e "${GREEN}║   AI Toolkit rules enforced 100% consistency!                           ║${NC}"
    echo -e "${GREEN}║                                                                          ║${NC}"
    echo -e "${GREEN}╚══════════════════════════════════════════════════════════════════════════╝${NC}"
else
    echo -e "${YELLOW}╔══════════════════════════════════════════════════════════════════════════╗${NC}"
    echo -e "${YELLOW}║                                                                          ║${NC}"
    echo -e "${YELLOW}║   ⚠️  DIFFERENCES FOUND - Check output above                            ║${NC}"
    echo -e "${YELLOW}║                                                                          ║${NC}"
    echo -e "${YELLOW}║   Run: diff worktrees/dev1-minh/... worktrees/dev2-linh/... for details ║${NC}"
    echo -e "${YELLOW}║                                                                          ║${NC}"
    echo -e "${YELLOW}╚══════════════════════════════════════════════════════════════════════════╝${NC}"
fi

echo ""

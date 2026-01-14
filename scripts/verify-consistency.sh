#!/bin/bash

# =============================================================================
# VERIFICATION SCRIPT: Compare code from 5 developers
# =============================================================================
# Run this script after all 5 developers have committed their code
# =============================================================================

set -e

echo "╔══════════════════════════════════════════════════════════════════════╗"
echo "║  AI TOOLKIT VERIFICATION: Comparing 5 Developers' Code              ║"
echo "╚══════════════════════════════════════════════════════════════════════╝"
echo ""

# Colors
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

# Configuration - Change these to match your test
SERVICE_FILE="src/main/java/com/vtrip/booking/service/impl/BookingServiceImpl.java"
BRANCHES=("test/dev1-senior" "test/dev2-junior" "test/dev3-nodejs" "test/dev4-ddd" "test/dev5-mid")

# Create comparison directory
COMPARE_DIR="comparison_$(date +%Y%m%d_%H%M%S)"
mkdir -p "$COMPARE_DIR"
cd "$COMPARE_DIR"

echo -e "${YELLOW}[1/5] Fetching all branches...${NC}"
git fetch --all --quiet

echo -e "${YELLOW}[2/5] Extracting code from each branch...${NC}"
for branch in "${BRANCHES[@]}"; do
    echo "  Extracting from $branch..."
    git show "$branch:$SERVICE_FILE" > "${branch##*/}.java" 2>/dev/null || \
        echo "  ⚠️  Could not find $SERVICE_FILE in $branch"
done

echo ""
echo "═══════════════════════════════════════════════════════════════════════"
echo -e "${BLUE}[3/5] DIFF COMPARISON${NC}"
echo "═══════════════════════════════════════════════════════════════════════"
echo ""

FIRST_FILE="${BRANCHES[0]##*/}.java"
ALL_IDENTICAL=true

for branch in "${BRANCHES[@]:1}"; do
    FILE="${branch##*/}.java"
    if [ -f "$FILE" ] && [ -f "$FIRST_FILE" ]; then
        if diff -q "$FIRST_FILE" "$FILE" > /dev/null 2>&1; then
            echo -e "  ${GREEN}✓${NC} ${BRANCHES[0]##*/} vs ${branch##*/}: ${GREEN}IDENTICAL${NC}"
        else
            echo -e "  ${RED}✗${NC} ${BRANCHES[0]##*/} vs ${branch##*/}: ${RED}DIFFERENT${NC}"
            ALL_IDENTICAL=false
            echo "    Differences:"
            diff "$FIRST_FILE" "$FILE" | head -10
        fi
    fi
done

echo ""
echo "═══════════════════════════════════════════════════════════════════════"
echo -e "${BLUE}[4/5] MD5 CHECKSUMS${NC}"
echo "═══════════════════════════════════════════════════════════════════════"
echo ""

for file in *.java; do
    if [ -f "$file" ]; then
        echo "  $(md5 -r "$file")"
    fi
done

echo ""
echo "═══════════════════════════════════════════════════════════════════════"
echo -e "${BLUE}[5/5] PATTERN ANALYSIS${NC}"
echo "═══════════════════════════════════════════════════════════════════════"
echo ""

echo -e "${YELLOW}DI Pattern:${NC}"
grep -h "@RequiredArgsConstructor\|@Autowired" *.java 2>/dev/null | sort | uniq -c || echo "  No matches"

echo ""
echo -e "${YELLOW}Exception Types:${NC}"
grep -h "throw new\|NotFoundException\|RuntimeException\|Exception" *.java 2>/dev/null | sort | uniq -c || echo "  No matches"

echo ""
echo -e "${YELLOW}Mapper Usage:${NC}"
grep -h "Mapper\|mapper\|BeanUtils" *.java 2>/dev/null | sort | uniq -c || echo "  No matches"

echo ""
echo "═══════════════════════════════════════════════════════════════════════"
echo -e "${BLUE}FINAL RESULT${NC}"
echo "═══════════════════════════════════════════════════════════════════════"
echo ""

if [ "$ALL_IDENTICAL" = true ]; then
    echo -e "${GREEN}╔══════════════════════════════════════════════════════════════════════╗${NC}"
    echo -e "${GREEN}║                                                                      ║${NC}"
    echo -e "${GREEN}║   ✅ SUCCESS: ALL 5 DEVELOPERS PRODUCED IDENTICAL CODE!            ║${NC}"
    echo -e "${GREEN}║                                                                      ║${NC}"
    echo -e "${GREEN}║   AI Toolkit rules enforced 100% consistency!                       ║${NC}"
    echo -e "${GREEN}║                                                                      ║${NC}"
    echo -e "${GREEN}╚══════════════════════════════════════════════════════════════════════╝${NC}"
else
    echo -e "${RED}╔══════════════════════════════════════════════════════════════════════╗${NC}"
    echo -e "${RED}║                                                                      ║${NC}"
    echo -e "${RED}║   ⚠️  SOME DIFFERENCES FOUND - Review output above                  ║${NC}"
    echo -e "${RED}║                                                                      ║${NC}"
    echo -e "${RED}╚══════════════════════════════════════════════════════════════════════╝${NC}"
fi

echo ""
echo "Comparison files saved in: $(pwd)"
echo ""

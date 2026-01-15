#!/bin/bash

# =============================================================================
# VERIFY: Compare code between dev1-minh and dev2-linh only
# =============================================================================

set -e

echo "╔══════════════════════════════════════════════════════════════════════════╗"
echo "║  VERIFICATION: Comparing dev1-minh vs dev2-linh                         ║"
echo "╚══════════════════════════════════════════════════════════════════════════╝"
echo ""

# Colors
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

DEMO_DIR="/Users/thaitrn/Projects/ai-toolkit-demo"
DEV1_DIR="$DEMO_DIR/worktrees/dev1-minh"
DEV2_DIR="$DEMO_DIR/worktrees/dev2-linh"

# Find all Java files in both directories
echo -e "${BLUE}[1/4] Finding Java files...${NC}"
echo ""

DEV1_FILES=$(find "$DEV1_DIR/src" -name "*.java" 2>/dev/null | sort)
DEV2_FILES=$(find "$DEV2_DIR/src" -name "*.java" 2>/dev/null | sort)

if [ -z "$DEV1_FILES" ] && [ -z "$DEV2_FILES" ]; then
    echo -e "${YELLOW}No Java files found yet. Make sure developers have generated code.${NC}"
    echo ""
    echo "Looking for any files in src/..."
    echo ""
    echo "dev1-minh:"
    find "$DEV1_DIR/src" -type f 2>/dev/null || echo "  No src/ directory"
    echo ""
    echo "dev2-linh:"
    find "$DEV2_DIR/src" -type f 2>/dev/null || echo "  No src/ directory"
    exit 0
fi

echo "dev1-minh files:"
echo "$DEV1_FILES" | sed 's|.*/src/|  src/|'
echo ""
echo "dev2-linh files:"
echo "$DEV2_FILES" | sed 's|.*/src/|  src/|'

echo ""
echo "═══════════════════════════════════════════════════════════════════════════"
echo -e "${BLUE}[2/4] DIFF COMPARISON${NC}"
echo "═══════════════════════════════════════════════════════════════════════════"
echo ""

ALL_IDENTICAL=true

# Compare each file
for file1 in $DEV1_FILES; do
    relative_path="${file1#$DEV1_DIR/}"
    file2="$DEV2_DIR/$relative_path"
    filename=$(basename "$file1")
    
    if [ -f "$file2" ]; then
        if diff -q "$file1" "$file2" > /dev/null 2>&1; then
            echo -e "  ${GREEN}✓${NC} $filename: ${GREEN}IDENTICAL${NC}"
        else
            echo -e "  ${RED}✗${NC} $filename: ${RED}DIFFERENT${NC}"
            ALL_IDENTICAL=false
            echo ""
            echo "    Differences:"
            diff "$file1" "$file2" | head -20 | sed 's/^/    /'
            echo ""
        fi
    else
        echo -e "  ${YELLOW}⚠${NC} $filename: Missing in dev2-linh"
        ALL_IDENTICAL=false
    fi
done

echo ""
echo "═══════════════════════════════════════════════════════════════════════════"
echo -e "${BLUE}[3/4] MD5 CHECKSUMS${NC}"
echo "═══════════════════════════════════════════════════════════════════════════"
echo ""

for file1 in $DEV1_FILES; do
    relative_path="${file1#$DEV1_DIR/}"
    file2="$DEV2_DIR/$relative_path"
    filename=$(basename "$file1")
    
    if [ -f "$file1" ] && [ -f "$file2" ]; then
        hash1=$(md5 -q "$file1")
        hash2=$(md5 -q "$file2")
        echo "  $filename:"
        echo "    dev1-minh: $hash1"
        echo "    dev2-linh: $hash2"
        if [ "$hash1" = "$hash2" ]; then
            echo -e "    → ${GREEN}MATCH${NC}"
        else
            echo -e "    → ${RED}MISMATCH${NC}"
        fi
        echo ""
    fi
done

echo "═══════════════════════════════════════════════════════════════════════════"
echo -e "${BLUE}[4/4] PATTERN CHECK${NC}"
echo "═══════════════════════════════════════════════════════════════════════════"
echo ""

echo -e "${YELLOW}DI Pattern:${NC}"
echo -n "  dev1-minh: "
grep -h "@RequiredArgsConstructor\|@Autowired" $DEV1_FILES 2>/dev/null | head -1 || echo "Not found"
echo -n "  dev2-linh: "
grep -h "@RequiredArgsConstructor\|@Autowired" $DEV2_FILES 2>/dev/null | head -1 || echo "Not found"

echo ""
echo -e "${YELLOW}Exception Type:${NC}"
echo -n "  dev1-minh: "
grep -oh "NotFoundException\|RuntimeException\|IllegalArgumentException" $DEV1_FILES 2>/dev/null | head -1 || echo "Not found"
echo -n "  dev2-linh: "
grep -oh "NotFoundException\|RuntimeException\|IllegalArgumentException" $DEV2_FILES 2>/dev/null | head -1 || echo "Not found"

echo ""
echo "═══════════════════════════════════════════════════════════════════════════"
echo -e "${BLUE}FINAL RESULT${NC}"
echo "═══════════════════════════════════════════════════════════════════════════"
echo ""

if [ "$ALL_IDENTICAL" = true ]; then
    echo -e "${GREEN}╔══════════════════════════════════════════════════════════════════════════╗${NC}"
    echo -e "${GREEN}║                                                                          ║${NC}"
    echo -e "${GREEN}║   ✅ SUCCESS: dev1-minh và dev2-linh có CODE IDENTICAL!                ║${NC}"
    echo -e "${GREEN}║                                                                          ║${NC}"
    echo -e "${GREEN}║   AI Toolkit rules enforced consistency!                                ║${NC}"
    echo -e "${GREEN}║                                                                          ║${NC}"
    echo -e "${GREEN}╚══════════════════════════════════════════════════════════════════════════╝${NC}"
else
    echo -e "${YELLOW}╔══════════════════════════════════════════════════════════════════════════╗${NC}"
    echo -e "${YELLOW}║                                                                          ║${NC}"
    echo -e "${YELLOW}║   ⚠️  DIFFERENCES FOUND - Check output above                            ║${NC}"
    echo -e "${YELLOW}║                                                                          ║${NC}"
    echo -e "${YELLOW}╚══════════════════════════════════════════════════════════════════════════╝${NC}"
fi

echo ""

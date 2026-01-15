#!/bin/bash

# =============================================================================
# VERIFY: Compare code between dev1-minh, dev2-linh, and dev3-hung
# =============================================================================

set -e

echo "╔══════════════════════════════════════════════════════════════════════════╗"
echo "║  VERIFICATION: Comparing dev1-minh vs dev2-linh vs dev3-hung             ║"
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
DEV3_DIR="$DEMO_DIR/worktrees/dev3-hung"

# Find all Java files in all directories
echo -e "${BLUE}[1/4] Finding Java files...${NC}"
echo ""

DEV1_FILES=$(find "$DEV1_DIR/src" -name "*.java" 2>/dev/null | sort)
DEV2_FILES=$(find "$DEV2_DIR/src" -name "*.java" 2>/dev/null | sort)
DEV3_FILES=$(find "$DEV3_DIR/src" -name "*.java" 2>/dev/null | sort)

if [ -z "$DEV1_FILES" ] && [ -z "$DEV2_FILES" ] && [ -z "$DEV3_FILES" ]; then
    echo -e "${YELLOW}No Java files found yet. Make sure developers have generated code.${NC}"
    echo ""
    echo "Looking for any files in src/..."
    echo ""
    echo "dev1-minh:"
    find "$DEV1_DIR/src" -type f 2>/dev/null || echo "  No src/ directory"
    echo ""
    echo "dev2-linh:"
    find "$DEV2_DIR/src" -type f 2>/dev/null || echo "  No src/ directory"
    echo ""
    echo "dev3-hung:"
    find "$DEV3_DIR/src" -type f 2>/dev/null || echo "  No src/ directory"
    exit 0
fi

DEV1_COUNT=$(echo "$DEV1_FILES" | grep -c . || echo "0")
DEV2_COUNT=$(echo "$DEV2_FILES" | grep -c . || echo "0")
DEV3_COUNT=$(echo "$DEV3_FILES" | grep -c . || echo "0")

echo "dev1-minh: $DEV1_COUNT files"
echo "$DEV1_FILES" | sed 's|.*/src/|  src/|'
echo ""
echo "dev2-linh: $DEV2_COUNT files"
echo "$DEV2_FILES" | sed 's|.*/src/|  src/|'
echo ""
echo "dev3-hung: $DEV3_COUNT files"
echo "$DEV3_FILES" | sed 's|.*/src/|  src/|'

echo ""
echo "═══════════════════════════════════════════════════════════════════════════"
echo -e "${BLUE}[2/4] DIFF COMPARISON${NC}"
echo "═══════════════════════════════════════════════════════════════════════════"
echo ""

ALL_IDENTICAL=true
DEV1_VS_DEV2_IDENTICAL=true
DEV1_VS_DEV3_IDENTICAL=true

# Compare dev1 vs dev2
echo -e "${YELLOW}── dev1-minh vs dev2-linh ──${NC}"
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
            DEV1_VS_DEV2_IDENTICAL=false
            diff "$file1" "$file2" | head -10 | sed 's/^/    /'
        fi
    else
        echo -e "  ${YELLOW}⚠${NC} $filename: Missing in dev2-linh"
        ALL_IDENTICAL=false
        DEV1_VS_DEV2_IDENTICAL=false
    fi
done

echo ""
echo -e "${YELLOW}── dev1-minh vs dev3-hung ──${NC}"
for file1 in $DEV1_FILES; do
    relative_path="${file1#$DEV1_DIR/}"
    file3="$DEV3_DIR/$relative_path"
    filename=$(basename "$file1")
    
    if [ -f "$file3" ]; then
        if diff -q "$file1" "$file3" > /dev/null 2>&1; then
            echo -e "  ${GREEN}✓${NC} $filename: ${GREEN}IDENTICAL${NC}"
        else
            echo -e "  ${RED}✗${NC} $filename: ${RED}DIFFERENT${NC}"
            ALL_IDENTICAL=false
            DEV1_VS_DEV3_IDENTICAL=false
            diff "$file1" "$file3" | head -10 | sed 's/^/    /'
        fi
    else
        echo -e "  ${YELLOW}⚠${NC} $filename: Missing in dev3-hung"
        ALL_IDENTICAL=false
        DEV1_VS_DEV3_IDENTICAL=false
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
    file3="$DEV3_DIR/$relative_path"
    filename=$(basename "$file1")
    
    echo "  $filename:"
    
    if [ -f "$file1" ]; then
        hash1=$(md5 -q "$file1")
        echo "    dev1-minh: $hash1"
    else
        echo "    dev1-minh: (missing)"
    fi
    
    if [ -f "$file2" ]; then
        hash2=$(md5 -q "$file2")
        echo "    dev2-linh: $hash2"
    else
        echo "    dev2-linh: (missing)"
    fi
    
    if [ -f "$file3" ]; then
        hash3=$(md5 -q "$file3")
        echo "    dev3-hung: $hash3"
    else
        echo "    dev3-hung: (missing)"
    fi
    
    if [ -f "$file1" ] && [ -f "$file2" ] && [ -f "$file3" ]; then
        if [ "$hash1" = "$hash2" ] && [ "$hash1" = "$hash3" ]; then
            echo -e "    → ${GREEN}ALL MATCH${NC}"
        else
            echo -e "    → ${RED}MISMATCH${NC}"
        fi
    fi
    echo ""
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
echo -n "  dev3-hung: "
grep -h "@RequiredArgsConstructor\|@Autowired" $DEV3_FILES 2>/dev/null | head -1 || echo "Not found"

echo ""
echo -e "${YELLOW}Exception Type:${NC}"
echo -n "  dev1-minh: "
grep -oh "NotFoundException\|RuntimeException\|IllegalArgumentException" $DEV1_FILES 2>/dev/null | head -1 || echo "Not found"
echo -n "  dev2-linh: "
grep -oh "NotFoundException\|RuntimeException\|IllegalArgumentException" $DEV2_FILES 2>/dev/null | head -1 || echo "Not found"
echo -n "  dev3-hung: "
grep -oh "NotFoundException\|RuntimeException\|IllegalArgumentException" $DEV3_FILES 2>/dev/null | head -1 || echo "Not found"

echo ""
echo "═══════════════════════════════════════════════════════════════════════════"
echo -e "${BLUE}FINAL RESULT${NC}"
echo "═══════════════════════════════════════════════════════════════════════════"
echo ""

if [ "$ALL_IDENTICAL" = true ]; then
    echo -e "${GREEN}╔══════════════════════════════════════════════════════════════════════════╗${NC}"
    echo -e "${GREEN}║                                                                          ║${NC}"
    echo -e "${GREEN}║   ✅ SUCCESS: All 3 developers have IDENTICAL CODE!                     ║${NC}"
    echo -e "${GREEN}║                                                                          ║${NC}"
    echo -e "${GREEN}║   dev1-minh = dev2-linh = dev3-hung                                     ║${NC}"
    echo -e "${GREEN}║                                                                          ║${NC}"
    echo -e "${GREEN}║   AI Toolkit rules enforced 100% consistency!                           ║${NC}"
    echo -e "${GREEN}║                                                                          ║${NC}"
    echo -e "${GREEN}╚══════════════════════════════════════════════════════════════════════════╝${NC}"
else
    echo -e "${YELLOW}╔══════════════════════════════════════════════════════════════════════════╗${NC}"
    echo -e "${YELLOW}║                                                                          ║${NC}"
    echo -e "${YELLOW}║   ⚠️  DIFFERENCES FOUND                                                  ║${NC}"
    echo -e "${YELLOW}║                                                                          ║${NC}"
    if [ "$DEV1_VS_DEV2_IDENTICAL" = true ]; then
        echo -e "${YELLOW}║   dev1-minh vs dev2-linh: ${GREEN}IDENTICAL${YELLOW}                                  ║${NC}"
    else
        echo -e "${YELLOW}║   dev1-minh vs dev2-linh: ${RED}DIFFERENT${YELLOW}                                  ║${NC}"
    fi
    if [ "$DEV1_VS_DEV3_IDENTICAL" = true ]; then
        echo -e "${YELLOW}║   dev1-minh vs dev3-hung: ${GREEN}IDENTICAL${YELLOW}                                  ║${NC}"
    else
        echo -e "${YELLOW}║   dev1-minh vs dev3-hung: ${RED}DIFFERENT${YELLOW}                                  ║${NC}"
    fi
    echo -e "${YELLOW}║                                                                          ║${NC}"
    echo -e "${YELLOW}╚══════════════════════════════════════════════════════════════════════════╝${NC}"
fi

echo ""

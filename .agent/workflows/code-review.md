---
description: AI-powered code review workflow
---

# Code Review Workflow

Sử dụng workflow này để thực hiện code review toàn diện.

## Steps

1. Identify the files to review
   - Ask user for PR link or file paths
   - Or use `git diff` to find changed files

// turbo
2. Check out the branch (if PR)
```bash
gh pr checkout <PR_NUMBER>
```

3. Analyze the changes
   - Run `@code-review` rule
   - Check for security issues
   - Check for performance issues
   - Check for code quality

4. Run tests
```bash
./gradlew test
```

5. Generate review summary
   - List all findings
   - Categorize by severity (HIGH, MEDIUM, LOW)
   - Provide recommendations

6. Output format
```markdown
## Code Review: [PR Title]

### Summary
- Files changed: X
- Lines added: +X
- Lines removed: -X

### Findings

#### 🔴 Critical
- ...

#### 🟡 Warnings  
- ...

#### 🟢 Suggestions
- ...

### Recommendations
1. ...
2. ...
```

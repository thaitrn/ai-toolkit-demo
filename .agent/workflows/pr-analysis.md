---
description: Analyze GitHub Pull Request
---

# PR Analysis Workflow

Phân tích Pull Request từ GitHub.

## Prerequisites
- GitHub CLI (`gh`) đã được cài đặt và authenticated

## Steps

1. Get PR details
// turbo
```bash
gh pr view <PR_NUMBER> --json title,body,files,additions,deletions,author
```

2. List changed files
// turbo
```bash
gh pr diff <PR_NUMBER> --name-only
```

3. View the diff
// turbo
```bash
gh pr diff <PR_NUMBER>
```

4. Check PR status
// turbo
```bash
gh pr checks <PR_NUMBER>
```

5. Analyze and provide feedback
   - Review each changed file
   - Apply relevant rules from `.cursor/rules/`
   - Check for breaking changes
   - Verify test coverage

6. Post review (optional)
```bash
gh pr review <PR_NUMBER> --comment --body "Review comments..."
```

## Output

Provide a comprehensive analysis including:
- PR summary
- Risk assessment
- Code quality observations
- Suggestions for improvement
- Approval recommendation

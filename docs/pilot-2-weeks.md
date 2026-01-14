# Quick Start Guide - AI Toolkit Pilot (2 Weeks)

## Mục tiêu
Chứng minh hiệu quả của AI Toolkit trong 2 tuần với team 10 backend engineers.

---

## Week 1: Setup & Baseline

### Day 1-2: Preparation

#### 1. Measure Baseline Metrics
```bash
# Collect data từ 2 sprints gần nhất:
- Average PR review time
- Number of review comments/PR
- Revision cycles/PR
- Code consistency issues/sprint
- Rework hours/sprint
```

#### 2. Deploy AI Toolkit
```bash
# Copy toolkit vào pilot project
cp -r deep-bohr/.cursor pilot-project/
cp -r deep-bohr/.agent pilot-project/
cp deep-bohr/AGENTS.md pilot-project/

# Commit
cd pilot-project
git add .cursor .agent AGENTS.md
git commit -m "feat: add AI Toolkit for team productivity"
```

#### 3. Setup Team Rules (Admin)
1. Go to [Cursor Dashboard](https://cursor.com/dashboard?tab=team-content)
2. Add rules from `docs/team-rules-templates.md`:
   - ✅ Company Coding Standards (Enforce)
   - ✅ Security Guidelines (Enforce)

### Day 3-4: Training

#### Quick Training Session (30 mins)
```markdown
Agenda:
1. Giới thiệu AGENTS.md (5 mins)
2. Demo @code-review (10 mins)
3. Demo /new-service workflow (10 mins)
4. Q&A (5 mins)
```

#### Team Assignment
| Developer | Task |
|-----------|------|
| Dev 1-3 | Dùng AI Toolkit cho feature mới |
| Dev 4-6 | Dùng @code-review trước khi tạo PR |
| Dev 7-8 | Test /new-service cho module mới |
| Dev 9-10 | Control group (không dùng AI) |

### Day 5: First Metrics

```bash
# Collect after 1 day:
- PR submitted with AI: X
- PR submitted without AI (control): Y
- Time to first review: compare
- Initial comments: compare
```

---

## Week 1: Daily Tracking

### Daily Stand-up Addition (2 mins)
```
"Hôm qua tôi dùng AI Toolkit cho [task], 
 tiết kiệm được [X] phút/giờ so với bình thường"
```

### Tracking Sheet

| Date | Dev | Task | AI Rule Used | Time Saved | Notes |
|------|-----|------|--------------|------------|-------|
| Day 1 | Minh | Review PR #42 | @code-review | 1.5h | Caught 5 issues |
| Day 1 | Linh | New endpoint | 01-core-architecture | 30min | Auto-structured |
| ... | ... | ... | ... | ... | ... |

---

## Week 2: Scale & Measure

### Day 6-8: Full Team Adoption

```bash
# Tất cả 10 devs dùng AI Toolkit
# Remove control group
# Focus: consistency và velocity
```

### Day 9-10: Measure & Compare

#### Metrics Comparison Template

```markdown
## Pilot Results: Week 1-2

### Code Review
| Metric | Baseline | Week 1 | Week 2 | Change |
|--------|----------|--------|--------|--------|
| Review time/PR | 2h | | | |
| Comments/PR | 8 | | | |
| Revision cycles | 2.5 | | | |

### Code Quality
| Metric | Baseline | Week 1 | Week 2 | Change |
|--------|----------|--------|--------|--------|
| Consistency issues | 15/sprint | | | |
| Rework hours | 22h | | | |

### Velocity
| Metric | Baseline | Week 1 | Week 2 | Change |
|--------|----------|--------|--------|--------|
| Features delivered | X | | | |
| Story points | Y | | | |

### Developer Satisfaction
Survey (1-5):
- "AI Toolkit giúp tôi code nhanh hơn": ___
- "Code review dễ dàng hơn": ___
- "Tôi muốn tiếp tục dùng": ___
```

---

## End of Pilot: Report Template

```markdown
# AI Toolkit Pilot Report
## Team: VTrip Backend (10 Engineers)
## Duration: 2 Weeks

### Executive Summary
[1-2 sentences on overall success/failure]

### Key Metrics
- Review time: X% reduction
- Rework: X% reduction  
- Velocity: X% increase
- Satisfaction: X/5

### Top Benefits Observed
1. [Benefit 1 with example]
2. [Benefit 2 with example]
3. [Benefit 3 with example]

### Challenges & Improvements
1. [Challenge 1 → How we addressed]
2. [Challenge 2 → Recommendation]

### Recommendation
[ ] Continue with current setup
[ ] Expand to other projects
[ ] Customize rules for [specific needs]
[ ] Not recommended (reason: ___)

### ROI Calculation
- Hours saved: X
- Cost equivalent: $X
- Cursor cost: $400/month
- Net ROI: X%
```

---

## Success Criteria

### Minimum Success (Continue Pilot):
- [ ] Review time giảm ≥ 30%
- [ ] Team satisfaction ≥ 3.5/5
- [ ] No major blockers

### Target Success (Scale to All Projects):
- [ ] Review time giảm ≥ 50%
- [ ] Rework giảm ≥ 40%
- [ ] Team satisfaction ≥ 4/5
- [ ] Velocity tăng ≥ 20%

### Exceptional Success (Case Study Material):
- [ ] Review time giảm ≥ 70%
- [ ] Rework giảm ≥ 60%
- [ ] Team satisfaction ≥ 4.5/5
- [ ] Velocity tăng ≥ 40%

---

## FAQ During Pilot

### "AI suggest sai, tôi phải sửa"
→ Feedback để customize rule. Tạo issue với example.

### "Rules quá strict"
→ Điều chỉnh `alwaysApply: false` cho rules có thể tắt.

### "Không thấy improvement"
→ Check xem đã invoke rules đúng cách chưa. Review training.

### "Conflict với existing linter"
→ Rules bổ sung linter, không thay thế. Align 2 tools.

---

## Next Steps After Pilot

### If Success:
```bash
# 1. Roll out to all projects
for project in user-service booking-service payment-service; do
  cp -r deep-bohr/.cursor $project/
  cp -r deep-bohr/.agent $project/
  cp deep-bohr/AGENTS.md $project/
done

# 2. Document learnings
# 3. Share case study internally
# 4. Schedule monthly rules review
```

### If Partial Success:
```bash
# 1. Identify what worked, what didn't
# 2. Customize rules based on feedback
# 3. Extend pilot 1 more week
# 4. Retry with improvements
```

# Wang Zaixi - Project Portfolio Page

## Project Overview: FinBro

**Finbro** is a command-line personal finance tracker designed to help users manage their expenses efficiently. It allows
users to record expenses, monitor spending habits, set financial limits, and perform currency conversions.

My contributions focused on improving usability via interactive edit limit command flows, offline currency conversion, and flexible
natural-language date input.

---

## Code Contributions

**RepoSense Link:** [View detailed code contributions](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=WangZX2001&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2026-02-20T00%3A00%3A00&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&filteredFileName=)

---

## Core Features Implemented

### 1. Edit Limit Command (`edit limit`)

**What it does:** Updates the current monthly spending limit through an interactive flow, supporting three operations:
**increase**, **decrease**, and **replace**.

**Justification:** Users often adjust their budget mid-month. A guided workflow prevents accidental changes and makes it
harder to enter invalid values.

**User-facing flow (walkthrough mode):**
1. User enters `edit limit` (the command takes no additional parameters)
2. App prompts the user to choose an operation: increase/decrease/replace
3. App prompts for the amount and validates it (numeric, non-negative)
4. App shows the computed new limit and requests a final confirmation
5. App updates the limit only after confirmation

**Highlights:**
- Input validation for blank input, non-numeric input, and negative values
- Confirmation step to prevent accidental updates
- Logging (`java.util.logging`) added for traceability and debugging

**Key files:**
- `src/main/java/seedu/finbro/commands/EditLimitCommand.java`
- `src/main/java/seedu/finbro/parser/Parser.java`
- `src/main/java/seedu/finbro/ui/Ui.java`

---

### 2. Currency Conversion Command (`currency`)

**What it does:** Converts an existing expense amount from a source currency to a target currency via an interactive
workflow.

**Justification:** Users may log expenses while travelling or want to understand spending in their home currency. The
feature gives quick offline conversion results without relying on an external API.

**User-facing flow (walkthrough mode):**
1. User enters `currency`
2. App prompts for a source currency code
3. App prompts for a target currency code
4. App prompts the user to select an expense entry to convert
5. App displays the converted value (the original stored expense remains unchanged)

**Highlights:**
- Uses an offline, locally stored currency rate table (no internet required)
- Uses SGD as a base currency to keep conversion logic simple and avoid storing every currency pair
- Unsupported currencies are rejected with a clear error message that includes supported currency codes

**Key files:**
- `src/main/java/seedu/finbro/commands/CurrencyCommand.java`
- `src/main/java/seedu/finbro/utils/CurrencyRateTable.java`
- `src/main/java/seedu/finbro/ui/Ui.java`

![Currency Command Sequence Diagram](../UML_diagrams/images/CurrencyCommand.png)

---

## Enhancement Implemented

### 3. Natural Date Parsing Utility

**What it does:** Accepts flexible, human-readable date inputs and converts them into a standard `LocalDate` used by
Finbro internally.

**Justification:** Strict date formats are a common source of friction in CLI apps. Natural input reduces formatting
errors and makes common user workflows faster.

**Supported inputs (examples):**
- Relative days: `today`, `tomorrow`, `yesterday`
- Relative durations: `2 days ago`, `3 days later`, `2 weeks ago`, `1 week later`
- Week references: `last week`, `next week`
- Day-of-week references: `last monday`, `next friday`
- Fallback: `yyyy-MM-dd`

**Implementation details:**
- Built using Java's `LocalDate` API for correct date arithmetic
- Uses regex matching to interpret relative day/week expressions
- Uses `TemporalAdjusters` to compute previous/next weekdays
- Normalises all supported inputs into a standardised internal format
- Enforces past-only dates for expense entry (future dates are rejected with an error message)
- Provides clear exceptions / error feedback for invalid inputs

**Key file:** `src/main/java/seedu/finbro/utils/NaturalDateParser.java`

---

## Testing Contributions

Added unit tests to cover the happy paths and important edge cases for my features:

- `src/test/java/seedu/finbro/commands/EditLimitCommandTest.java`
- `src/test/java/seedu/finbro/commands/CurrencyCommandTest.java`
- `src/test/java/seedu/finbro/utils/NaturalDateParserTest.java`
- `src/test/java/seedu/finbro/utils/CurrencyRateTableTest.java`
- `src/test/java/seedu/finbro/parser/ParserTest.java`

---

## Documentation Contributions

### User Guide

Contributed documentation for:
- `edit limit`: interactive workflow, examples, validation rules, and confirmation behaviour
- `currency`: guided prompts, supported currency behaviour, and offline-rate limitations
- Natural date input: supported formats (e.g. `today`, `2 days ago`, `last monday`) and `yyyy-MM-dd` fallback

### Developer Guide

Contributed documentation for:
- `edit limit` command flow and validation design considerations with sequence of action diagram
- Currency conversion design (base-currency mapping, workflow, and error handling) with sequence of action diagram
- Natural date parsing approach (`LocalDate`, regex patterns, and invalid-input handling)

---

## Team-Based Contributions

- Assisted with integrating interactive commands into the existing parser and UI architecture
- Helped improve consistency of validation handling across interactive command workflows
- Participated in testing and debugging across modules related to my features
- Contributed to refining user interaction flows to reduce user error

---

## Bug Identification (During PE Dry Run)

During the PE dry run, I helped to identify **10 bugs** in the other group's product that I was evaluating. This includes functional bugs, feature flaws, as well as documentation bugs.

**Image showing PE-D bugs identified:** ![Zaixi PE-D reported bugs](../UML_diagrams/images/Zaxi_PE_reported_bugs.png)

---

## Bug Fixing (Post PE Dry Run)

After the first PE dry run, I analysed incoming PE-D reports and focused on fixing issues that affected user-facing flows
and documentation clarity. This included fixes and doc updates around interactive command sessions (e.g., `edit limit`)
and improving the alignment between error messages and documented behaviour.

**Image showing closed issues after PE dry run:** ![Zaixi bug fixes after PE dry run](../UML_diagrams/images/Zaixi_bug_fix.png)

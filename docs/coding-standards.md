# Coding Standards

## Javadoc Standards
- Every class, interface, and method must include Javadoc.
- Each Javadoc block must contain:
  - Summary sentence.
  - Importance sentence starting with "Importance:".
  - Alternatives sentence starting with "Alternatives:" when applicable.

## Checkstyle
- Checkstyle enforces Javadoc presence for public types and methods.
- Run `mvn -q -DskipTests=false verify` before each commit.

## Commit Logging
Every commit must include:
1) A new entry in `docs/commit-log.md` using the CL-XXXX format.
2) A detailed change log entry in `docs/change-log-detailed/CL-XXXX.md`.

### CL-XXXX Template (commit-log.md)
- CL-0001 | YYYY-MM-DD | Short title | Author | Related issues

### CL-XXXX Template (change-log-detailed/CL-XXXX.md)
# CL-0001: Short title

## Summary
- One paragraph summary of the change.

## Motivation
- Why this change is needed.

## Changes
- Bullet list of concrete changes.

## Verification
- Commands executed and results.

## Risks
- Known risks and mitigations.

## Rollback
- Steps to revert or disable the change.

## Notes
- Links, references, or follow-ups.

# Domain Rules

## Identity and Profiles
- Every person profile requires a unique email or phone number at creation.
- Profiles start as unclaimed; once claimed, changes require approval or merge workflow.
- Alias names, secondary phones, and emails are supported and can be primary for others.

## Visibility and Grants
- Field-level visibility is controlled by hierarchy and explicit per-person grants.
- Person records are private by default; public visibility is opt-in only.

## Merge and Conflict Resolution
- Merges must preserve provenance and anonymize contributor identities.
- Relationship status is bi-directional with visible state (green/yellow/orange/red).
- Duplicate detection uses 90% name similarity plus shared email or phone, with a staged merge process.

## Sensitive Updates
- Admin review required for high-impact updates (e.g., caste, name after marriage).
- Sensitive fields (medical history, financial cards) use enhanced encryption and access control.

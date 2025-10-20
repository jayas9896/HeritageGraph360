# Security Model

## Identity and Access
- OAuth2 login for individual users.
- SAML SSO federation for institutional tenants.
- Role-based access control (RBAC) with tenant-scoped roles.
- RBAC entities persisted in SQL with role, permission, and mapping tables.
- Policy entities define fine-grained rules for field-level visibility.
- Least-privilege defaults with explicit elevation workflows.
- Method security enforces reviewer/admin access for audit, merge decisions, and sensitive fields.

## Public Access
- `/health` and `/info` endpoints are public.
- Optional `/public/profiles/{id}` endpoint for opt-in visibility.

## Approval Workflows
- Claimed profiles require approval for high-impact updates.
- Merge workflows preserve provenance and anonymize contributors.

## Sensitive Data
- Medical history and financial cards stored under enhanced encryption.
- Access logged to SQL audit tables and immutable event stores.

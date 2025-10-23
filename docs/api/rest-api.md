# REST API (Swagger/OpenAPI)

Base URL: `/api/v1`

## Public Endpoints
- `GET /public/profiles/{profileId}`: public profile summary (opt-in only).

## Authenticated Endpoints
- Headers: `x-tenant-id`, `x-actor-id`
- `POST /profiles`: create a person profile (requires unique email or phone).
- `POST /profiles/{profileId}/claim`: claim a profile.
- `POST /profiles/{profileId}/relationships`: create or update relationship.
- `POST /profiles/{profileId}/merge`: request a merge workflow.
- `POST /profiles/{profileId}/sensitive-fields`: store encrypted sensitive fields.
- `GET /profiles/{profileId}`: retrieve profile with field-level visibility.
- `POST /profiles/{profileId}/grants`: create field-level visibility grant.
- `GET /audit/{profileId}`: fetch audit trail.
- `POST /ingestion/records`: submit a single ingestion record.
- `GET /insights/{profileId}`: fetch insight summaries.

## Admin/Reviewer Endpoints
- Headers: `x-tenant-id`, `x-actor-id`
- `POST /approvals/{profileId}`: approve high-impact updates.
- `POST /merges/{mergeId}/decision`: accept or reject merge.
- `POST /tenants`: provision a new tenant.
- `GET /audit/{profileId}`: fetch audit trail (reviewer/admin).
- `POST /rbac/roles`: create a role (admin).
- `GET /rbac/roles`: list roles (admin).
- `POST /rbac/permissions`: create a permission (admin).
- `GET /rbac/permissions`: list permissions (admin).
- `POST /rbac/roles/{roleId}/permissions`: assign permission to role (admin).
- `POST /rbac/accounts/{accountId}/roles`: assign role to account (admin).

Swagger UI endpoints are exposed per service at `/swagger-ui.html`.

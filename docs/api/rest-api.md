# REST API (Swagger/OpenAPI)

Base URL: `/api/v1`

## Public Endpoints
- `GET /public/profiles/{profileId}`: public profile summary (opt-in only).

## Authenticated Endpoints
- `POST /profiles`: create a person profile (requires unique email or phone).
- `POST /profiles/{profileId}/claim`: claim a profile.
- `POST /profiles/{profileId}/relationships`: create or update relationship.
- `POST /profiles/{profileId}/merge`: request a merge workflow.
- `GET /audit/{profileId}`: fetch audit trail.
- `POST /ingestion/records`: submit a single ingestion record.
- `GET /insights/{profileId}`: fetch insight summaries.

## Admin/Reviewer Endpoints
- `POST /approvals/{profileId}`: approve high-impact updates.
- `POST /merges/{mergeId}/decision`: accept or reject merge.
- `POST /tenants`: provision a new tenant.

Swagger UI endpoints are exposed per service at `/swagger-ui.html`.

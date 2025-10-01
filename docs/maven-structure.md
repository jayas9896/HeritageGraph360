# Maven Multi-Module Structure

- `heritagegraph360-parent` (root POM)
  - `services/common-lib`
  - `services/identity-service`
  - `services/profile-service`
  - `services/ingestion-service`
  - `services/insights-service`
  - `services/gateway-service`

Each module inherits dependency management and Checkstyle rules from the parent POM.

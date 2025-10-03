package com.heritagegraph360.identity.api;

import com.heritagegraph360.identity.domain.TenantEntity;
import com.heritagegraph360.identity.repo.TenantRepository;
import java.util.regex.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Manages tenant provisioning endpoints.
 * Importance: Enables onboarding of new organizations into the platform.
 * Alternatives: Delegate tenant provisioning to an administrative back office.
 */
@RestController
@RequestMapping("/api/v1/tenants")
public class TenantController {
    private static final Pattern TENANT_PATTERN = Pattern.compile("org-[a-zA-Z0-9]+-[a-zA-Z0-9]+$");
    private final TenantRepository tenantRepository;

    /**
     * Creates a tenant controller.
     * Importance: Injects the tenant repository for provisioning workflows.
     * Alternatives: Use a service layer between controller and repository.
     *
     * @param tenantRepository the tenant repository.
     */
    public TenantController(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    /**
     * Creates a new tenant record.
     * Importance: Enforces tenant ID format and persists tenant metadata.
     * Alternatives: Require approvals before creating tenants.
     *
     * @param request the tenant creation request.
     * @return the tenant creation response.
     */
    @PostMapping
    public ResponseEntity<TenantResponse> createTenant(@RequestBody TenantRequest request) {
        if (!TENANT_PATTERN.matcher(request.getTenantId()).matches()) {
            return ResponseEntity.badRequest()
                .body(new TenantResponse("REJECTED", "Invalid tenant format"));
        }
        TenantEntity entity = new TenantEntity();
        entity.setTenantId(request.getTenantId());
        entity.setRegion(request.getRegion());
        entity.setOrgId(request.getOrgId());
        entity.setName(request.getName());
        tenantRepository.save(entity);
        return ResponseEntity.ok(new TenantResponse("CREATED", "Tenant created"));
    }
}

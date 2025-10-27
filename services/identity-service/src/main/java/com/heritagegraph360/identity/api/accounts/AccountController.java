package com.heritagegraph360.identity.api.accounts;

import com.heritagegraph360.identity.domain.AccountEntity;
import com.heritagegraph360.identity.repo.AccountRepository;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Manages account provisioning endpoints.
 * Importance: Enables tenant administrators to create accounts.
 * Alternatives: Use external identity provider provisioning only.
 */
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountRepository accountRepository;

    /**
     * Creates the account controller.
     * Importance: Connects HTTP requests to account persistence.
     * Alternatives: Use a dedicated account service.
     *
     * @param accountRepository the account repository.
     */
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Creates an account.
     * Importance: Supports tenant account provisioning.
     * Alternatives: Use external identity provider provisioning only.
     *
     * @param request the account request.
     * @return the account response.
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest request) {
        if ((request.getEmail() == null || request.getEmail().isBlank())
            && (request.getPhone() == null || request.getPhone().isBlank())) {
            return ResponseEntity.badRequest().body(new AccountResponse("", "REJECTED"));
        }
        if (request.getTenantId() == null || request.getTenantId().isBlank()) {
            return ResponseEntity.badRequest().body(new AccountResponse("", "REJECTED"));
        }
        if (request.getEmail() != null && accountRepository.existsByTenantIdAndEmail(request.getTenantId(), request.getEmail())) {
            return ResponseEntity.badRequest().body(new AccountResponse("", "DUPLICATE_EMAIL"));
        }
        if (request.getPhone() != null && accountRepository.existsByTenantIdAndPhone(request.getTenantId(), request.getPhone())) {
            return ResponseEntity.badRequest().body(new AccountResponse("", "DUPLICATE_PHONE"));
        }
        AccountEntity account = new AccountEntity();
        account.setAccountId(UUID.randomUUID());
        account.setTenantId(request.getTenantId());
        account.setEmail(request.getEmail());
        account.setPhone(request.getPhone());
        account.setDisplayName(request.getDisplayName());
        account.setStatus(request.getStatus() == null ? "ACTIVE" : request.getStatus());
        accountRepository.save(account);
        return ResponseEntity.ok(new AccountResponse(account.getAccountId().toString(), "CREATED"));
    }
}

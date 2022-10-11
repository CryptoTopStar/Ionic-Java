
  package com.smart.audit;
  
  import java.util.Optional;
  
  import org.springframework.data.domain.AuditorAware; import
  org.springframework.security.core.context.SecurityContextHolder;
  
  public class AuditorAwareImpl implements AuditorAware<String>{
  
  @Override public Optional<String> getCurrentAuditor() { 
	 if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
			  return Optional.of(
					  SecurityContextHolder.getContext().getAuthentication() != null ? 
					  SecurityContextHolder.getContext().getAuthentication().getName() : null);
	 }else {
		 return Optional.of("esp-client");
	 }
			 
	  }
  
  }
 
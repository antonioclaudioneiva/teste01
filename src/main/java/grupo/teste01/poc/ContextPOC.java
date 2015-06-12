package grupo.teste01.poc;

import java.util.List;

import br.gov.frameworkdemoiselle.context.CustomContext;
import br.gov.frameworkdemoiselle.context.RequestContext;
import br.gov.frameworkdemoiselle.internal.bootstrap.CustomContextBootstrap;
import br.gov.frameworkdemoiselle.util.Beans;

public class ContextPOC {
	public static RequestContext getRequestContext() {
		CustomContextBootstrap context = Beans.getReference(CustomContextBootstrap.class);
		List<CustomContext> customContexts = context.getCustomContexts();
		for (CustomContext customContext : customContexts) {
			if (customContext instanceof RequestContext) {
				return (RequestContext) customContext;
			}
		}
		return null;
	}

}

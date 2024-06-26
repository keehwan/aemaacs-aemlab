package com.community.aemlab.core.listeners.sample;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.sling.api.resource.observation.ResourceChange;
import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.jcr.JcrConstants;

/**
 * @author arunpatidar02
 *
 */
@Component(service = ResourceChangeListener.class, immediate = true)
@Designate(ocd = SampleResourceChangeListener.Config.class)
public class SampleResourceChangeListener implements ResourceChangeListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(SampleResourceChangeListener.class);

	@Override
	public void onChange(List<ResourceChange> list) {
		LOGGER.debug("On add/change of jcr:lastModified, listener activated with config via OCD");
	}

	@Activate
	protected void activate(SampleResourceChangeListener.Config configValues) {
		String config = ArrayUtils.toString(configValues.resource_paths());
		LOGGER.trace("Config values={}", config);
	}

	/**
	 * @author arunpatidar02
	 *
	 */
	@ObjectClassDefinition(name = "Annotation Demo SampleResourceChangeListener - OSGi", description = "Resource change Listener Registration properties")
	public @interface Config {
		@AttributeDefinition(name = "Paths", description = "ResourceChangeListener Paths property")
		String[] resource_paths() default { "/content/aemlab/oneweb/dam/import", "/content/aemlab/oneweb/import" };

		@AttributeDefinition(name = "Changes", description = "ResourceChangeListener Changes property")
		String[] resource_change_types() default { "ADDED", "CHANGED" };

		@AttributeDefinition(name = "Properties", description = "ResourceChangeListener PropertyNamesHint property")
		String[] resource_property_names_hint() default { JcrConstants.JCR_LASTMODIFIED };
	}

}
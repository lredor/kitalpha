/*******************************************************************************
 * Copyright (c) 2016, 2018 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *   Thales Global Services S.A.S - initial API and implementation
 *******************************************************************************/

package org.polarsys.kitalpha.ad.viewpoint.ui.provider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.polarsys.kitalpha.ad.common.AD_Log;

/**
 * @author Thomas Guiu
 *
 */
public interface AFContextProvider {

	ResourceSet computeContext(IWorkbenchPart part, ISelection selection);
	
	Helper INSTANCE = new Helper();
	
	class Helper {
		
		public List<AFContextProvider> getProviders() {
			List<AFContextProvider> result = new ArrayList<>();
			for (IConfigurationElement elt : Platform.getExtensionRegistry().getConfigurationElementsFor("org.polarsys.kitalpha.ad.viewpoint.ui.provider.context")) {
				try {
					AFContextProvider prov = (AFContextProvider)elt.createExecutableExtension("class");
					result.add(prov);
				} catch (CoreException e) {
					AD_Log.getDefault().logError(e);
				}
			}
			return result;
		}
	}
}

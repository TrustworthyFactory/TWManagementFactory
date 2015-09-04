/*
 *		OPTET Factory
 *
 *	Class TWCertificategenerationImpl 1.0 30 oct. 2013
 *
 *	Copyright (c) 2013 Thales Communications & Security SAS
 *	4, Avenue des Louvresses - 92230 Gennevilliers 
 *	All rights reserved
 *
 */

package com.thalesgroup.optet.twmanagement.impl;

import java.util.List;

import com.thalesgroup.optet.twmanagement.Asset;
import com.thalesgroup.optet.twmanagement.CertifiedEvidence;
import com.thalesgroup.optet.twmanagement.Component;
import com.thalesgroup.optet.twmanagement.Control;
import com.thalesgroup.optet.twmanagement.Metric;
import com.thalesgroup.optet.twmanagement.MetricCalculationMethod;
import com.thalesgroup.optet.twmanagement.ProblemDefinition;
import com.thalesgroup.optet.twmanagement.RuntimeEvidence;
import com.thalesgroup.optet.twmanagement.Stakeholder;
import com.thalesgroup.optet.twmanagement.TWCertificateGeneration;
import com.thalesgroup.optet.twmanagement.TWProperty;
import com.thalesgroup.optet.twmanagement.TWPropertySpecification;

/**
 * @author F. Motte
 *
 */
public class TWCertificategenerationImpl implements TWCertificateGeneration {

	/* (non-Javadoc)
	 * @see com.thalesgroup.optet.twmanagement.TWCertificateGeneration#setSystemDesciption(java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public void setSystemDesciption(List<Component> components,
			List<Stakeholder> stackholders, List<Asset> assets) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.thalesgroup.optet.twmanagement.TWCertificateGeneration#setTrustworthinessProblemDefinition(java.util.List)
	 */
	@Override
	public void setTrustworthinessProblemDefinition(
			List<ProblemDefinition> problemDefinition) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.thalesgroup.optet.twmanagement.TWCertificateGeneration#setTrustworthinessPropertySpecifications(java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public void setTrustworthinessPropertySpecifications(
			List<TWProperty> twProperties, List<Control> controls,
			List<TWPropertySpecification> twPropertySpec) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.thalesgroup.optet.twmanagement.TWCertificateGeneration#setEvidences(java.util.List, java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public void setEvidences(List<Metric> metrics,
			List<MetricCalculationMethod> metricCalculationMethods,
			List<CertifiedEvidence> certifiedEvidences,
			List<RuntimeEvidence> runtimeEvidences) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.thalesgroup.optet.twmanagement.TWCertificateGeneration#generateTWCertificate()
	 */
	@Override
	public void generateTWCertificate() {
		// TODO Auto-generated method stub

	}

}

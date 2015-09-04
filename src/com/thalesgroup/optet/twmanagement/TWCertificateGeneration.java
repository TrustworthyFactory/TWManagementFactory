/*
 *		OPTET Factory
 *
 *	Class TWCertificateGeneration 1.0 25 oct. 2013
 *
 *	Copyright (c) 2013 Thales Communications & Security SAS
 *	4, Avenue des Louvresses - 92230 Gennevilliers 
 *	All rights reserved
 *
 */

package com.thalesgroup.optet.twmanagement;

import java.util.List;

/**
 * @author F. Motte
 *
 */
public interface TWCertificateGeneration {
	
	/**
	 * Set the system description
	 * @param components the list of components present into the development
	 * @param stackholders the list of the stakeholder 
	 * @param assets the list of elements (or a part of it) being evaluated for its trustworthiness.
	 */
	public void setSystemDesciption(List<Component> components,
			List<Stakeholder> stackholders,
			List<Asset> assets);
	
	/**
	 * set the trustworthiness problems of the system
	 * @param problemDefinition the list of the definition problem
	 */
	public void setTrustworthinessProblemDefinition(
			List<ProblemDefinition> problemDefinition);
	
	/**
	 * set the trustworthiness properties sepcifications
	 * @param twProperties the list of the TW properties
	 * @param controls the list of the controls
	 * @param twPropertySpec the list of the TW properties specifications
	 */
	public void setTrustworthinessPropertySpecifications(
			List<TWProperty> twProperties,
			List<Control> controls,
			List<TWPropertySpecification> twPropertySpec);
	
	/**
	 * Set the evidences on the component
	 * @param metrics the list of the metrics of the profile
	 * @param metricCalculationMethods the list of the calculation methods
	 * @param certifiedEvidences the list of the certified evidences 
	 * @param runtimeEvidences the list of the runtime evidences
	 */
	public void setEvidences(List<Metric> metrics,
			List<MetricCalculationMethod> metricCalculationMethods,
			List<CertifiedEvidence> certifiedEvidences,
			List<RuntimeEvidence> runtimeEvidences);
	
	/**
	 * generated the certificate
	 */
	public void generateTWCertificate();
}

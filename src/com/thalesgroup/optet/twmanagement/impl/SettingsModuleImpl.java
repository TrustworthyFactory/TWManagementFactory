/*
 *		OPTET Factory
 *
 *	Class SettingsModuleImpl 1.0 30 oct. 2013
 *
 *	Copyright (c) 2013 Thales Communications & Security SAS
 *	4, Avenue des Louvresses - 92230 Gennevilliers 
 *	All rights reserved
 *
 */

package com.thalesgroup.optet.twmanagement.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.thalesgroup.optet.common.data.OptetDataModel;
import com.thalesgroup.optet.common.jaxb.TWProfile.TWProfile;
import com.thalesgroup.optet.common.jaxb.TWProfile.TWProfile.TWAttribute;
//import com.thalesgroup.optet.common.jaxb.TWProfile.TWProfile.TWAttribute.Metric;
import com.thalesgroup.optet.twmanagement.CSM;
import com.thalesgroup.optet.twmanagement.Evidence;
import com.thalesgroup.optet.twmanagement.Metric;
import com.thalesgroup.optet.twmanagement.ProjectInfo;
import com.thalesgroup.optet.twmanagement.Requirement;
import com.thalesgroup.optet.twmanagement.SettingsModule;



/**
 * @author F. Motte
 *
 */
public class SettingsModuleImpl implements SettingsModule {

	private Map<String,List<Evidence>> internalSettingsList ;

	private static SettingsModule INSTANCE = new SettingsModuleImpl();

	//private SVNWrapper svn;
	public static SettingsModule getInstance()
	{	return INSTANCE;
	}


	private SettingsModuleImpl() {
		super();
		internalSettingsList = new HashMap<String, List<Evidence>>();

		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.thalesgroup.optet.twmanagement.SettingsModule#setTWProfile(int, com.thalesgroup.optet.twmanagement.TWProfile)
	 */
	@Override
	public void setTWProfile(String projectID, TWProfile profile) {

		System.out.println("setTWProfile " + projectID);
		OptetDataModel.getInstance().cleanExpectedMetric();
		List<Evidence> evidences = new ArrayList<>();
		List<TWAttribute> twAttributes = profile.getTWAttribute();
		for (Iterator iterator = twAttributes.iterator(); iterator.hasNext();) {
			TWAttribute twAttribute = (TWAttribute) iterator.next();
			System.out.println("twAttribute " + twAttribute.getName());

			OptetDataModel.getInstance().addExpectedTWAttribute(twAttribute.getName(), twAttribute.getExpectedValue());

			Evidence currentEvidence = new Evidence(twAttribute.getName(), twAttribute.getAttributeID());
			List<com.thalesgroup.optet.common.jaxb.TWProfile.TWProfile.TWAttribute.Metric> metricList = twAttribute.getMetric();


			for (Iterator iterator2 = metricList.iterator(); iterator2
					.hasNext();) {
				com.thalesgroup.optet.common.jaxb.TWProfile.TWProfile.TWAttribute.Metric metric = (com.thalesgroup.optet.common.jaxb.TWProfile.TWProfile.TWAttribute.Metric) iterator2.next();
				OptetDataModel.getInstance().addExpectedMetric(metric.getName(), metric.getExpectedValue(), twAttribute.getName());
			}

			System.out.println("->>>>>" + twAttribute.getName());
			Set<String> list = OptetDataModel.getInstance().getEvidenceForTWAttribute(twAttribute.getName());
			for (Iterator iterator2 = list.iterator(); iterator2.hasNext();) {
				String metricName = (String) iterator2.next();

				System.out.println("metric " + metricName + " for " + twAttribute.getName());
				com.thalesgroup.optet.twmanagement.Metric m = new com.thalesgroup.optet.twmanagement.Metric();
				m.setMetricName(metricName);
				if (OptetDataModel.getInstance().getExpectedMetric(metricName, twAttribute.getName())!=null)
				{
					m.setMetricExpectedValue(OptetDataModel.getInstance().getExpectedMetric(metricName, twAttribute.getName()));
				}
				currentEvidence.addMetric(metricName, m);

			}


			evidences.add(currentEvidence);
		}
		internalSettingsList.put(projectID, evidences);

		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.thalesgroup.optet.twmanagement.SettingsModule#setCSM(int, com.thalesgroup.optet.twmanagement.CSM)
	 */
	@Override
	public void setCSM(String projectID, CSM csm) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.thalesgroup.optet.twmanagement.SettingsModule#setProjectInfos(int, java.util.List)
	 */
	@Override
	public void setProjectInfos(String projectID, List<ProjectInfo> infos) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.thalesgroup.optet.twmanagement.SettingsModule#getEvidences(int)
	 */
	@Override
	public List<Evidence> getEvidences(String projectID) {
		if (internalSettingsList.containsKey(projectID)){
			System.out.println("evidence found");
		}
		else
		{
			System.out.println("evidence not found");

			//setTWProfile(projectID, svn.getTWProfile(projectID));
		}
		// TODO Auto-generated method stub
		return internalSettingsList.get(projectID);
	}

	/* (non-Javadoc)
	 * @see com.thalesgroup.optet.twmanagement.SettingsModule#getRequirements(int)
	 */
	@Override
	public List<Requirement> getRequirements(String projectID) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.thalesgroup.optet.twmanagement.SettingsModule#getAvailableProjects()
	 */
	@Override
	public Set<String> getAvailableProjects(){
		return internalSettingsList.keySet();
	}

}

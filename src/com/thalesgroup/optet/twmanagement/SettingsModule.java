package com.thalesgroup.optet.twmanagement;

import java.util.List;
import java.util.Set;

import com.thalesgroup.optet.common.jaxb.TWProfile.TWProfile;



public interface SettingsModule {

	/**
	 * Set the TW profile
	 * @param projectID the ID of the project
	 * @param profile the TW profile of the project
	 */
	public void setTWProfile(String projectID, TWProfile profile);
	
	/**
	 * Set the CSM of the project
	 * @param projectID the ID of the project
	 * @param csm the CSM of the project
	 */
	public void setCSM(String projectID, CSM csm);
	

	/**
	 * Set added information of the project not present on the CSM and TW profile
	 * @param projectID the ID of the project
	 * @param infos list of information
	 */
	public void setProjectInfos(String projectID, List<ProjectInfo> infos);
	
	/**
	 * get the evidences required for the project
	 * @param projectID the ID of the project
	 * @return the list of the evidences required for the project
	 */
	public List<Evidence> getEvidences(String projectID);
	
	/**
	 * get the requirements for the project
	 * @param projectID the ID of the project
	 * @return the list of requirements for the project
	 */
	public List<Requirement> getRequirements(String projectID);
	
	
	/**
	 * get the list of the available project
	 * @return the list of all the available project
	 */
	public  Set<String> getAvailableProjects();
}

package com.thalesgroup.optet.twmanagement;

import java.nio.file.Path;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ISelection;

import com.thalesgroup.optet.plugin.template.jaxb.OptetPlugin;

public interface OrchestrationInterface {

	
	
	/**
	 * Register a new plugin
	 * @param pluginID the ID of the plugin
	 * @param evidences the evidences found by this plugin
	 * @param pref the preference list required by this plugin
	 */
	public void registerPlugin(String pluginID,
				OptetPlugin evidences,
				List<PreferencesConfiguration> pref);
	
	
	/**
	 * Analyze the given project 
	 * @param project the ID of the project
	 */
	public void staticAnalyse(IProject project);
	
	/**
	 * Analyze the given project 
	 * @param project the ID of the project
	 */
	public void staticJMLAnalyse(IProject project);
	
	/**
	 * Analyzes the given project 
	 * @param selection 
	 * @param projectID the ID of the project
	 */
	public void runtimeAnalyse(IProject project);
	
	
	public void staticAndRuntimeAnalyse(IProject project, String phase);
	
	/**
	 * called by the plugin when the staticanalysis is finished
	 * @param pluginID the ID of the plugin
	 */
	public void staticAnalyseFinished(String pluginID);
	
	/**
	 * called by the plugin when the staticanalysis is finished
	 * @param pluginID the ID of the plugin
	 */
	public void staticJMLAnalyseFinished(String pluginID);
	
	/**
	 * called by the plugin when the runtime analysis is finished
	 * @param pluginID the ID of the plugin
	 */
	public void runtimeAnalyseFinished(String pluginID);
	

	
}

/*
 *		OPTET Factory
 *
 *	Class OrchestrationImpl 1.0 30 oct. 2013
 *
 *	Copyright (c) 2013 Thales Communications & Security SAS
 *	4, Avenue des Louvresses - 92230 Gennevilliers 
 *	All rights reserved
 *
 */

package com.thalesgroup.optet.twmanagement.impl;

import java.io.IOException;
import java.util.Date;
//import org.eclipse.jst.ws.internal.common.ResourceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.internal.registry.osgi.Activator;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import com.thalesgroup.optet.common.data.OptetDataModel;
import com.thalesgroup.optet.plugin.template.jaxb.OptetPlugin;
import com.thalesgroup.optet.plugin.template.jaxb.OptetPlugin.Categories;
import com.thalesgroup.optet.twmanagement.Evidence;
import com.thalesgroup.optet.twmanagement.OrchestrationInterface;
import com.thalesgroup.optet.twmanagement.PluginHelper;
import com.thalesgroup.optet.twmanagement.PreferencesConfiguration;
import com.thalesgroup.optet.twmanagement.SettingsModule;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * @author F. Motte
 *
 */
public class OrchestrationImpl implements OrchestrationInterface {

	int nb = 0;

	List<String> runtimeAnalysisPlugin;
	List<String> staticAnalysisplugin;

	List<String> currentFinishedRuntimeAnalysisPlugin;
	List<String> currentFinishedStaticAnalysisplugin;

	String phase;

	boolean staticAndRuntime = false;
	boolean staticInProgress = false;
	boolean runtimeInProgress = false;
	boolean jmlInProgress = false;
	
	/** L'instance statique */
	private static OrchestrationInterface instance;

	private SettingsModule settings;

	public static OrchestrationInterface getInstance() {
		if (null == instance) { // Premier appel
			instance = new OrchestrationImpl();
		}
		return instance;
	}

	private OrchestrationImpl() {
		super();
		settings = SettingsModuleImpl.getInstance();
		runtimeAnalysisPlugin = new ArrayList<String>();
		staticAnalysisplugin = new ArrayList<String>();
		currentFinishedRuntimeAnalysisPlugin = new ArrayList<String>();
		currentFinishedStaticAnalysisplugin = new ArrayList<String>();
	}


	/* (non-Javadoc)
	 * @see com.thalesgroup.optet.twmanagement.OrchestrationInterface#registerPlugin(int, java.util.List, java.util.List)
	 */
	@Override
	public void registerPlugin(String pluginID, OptetPlugin evidences,
			List<PreferencesConfiguration> pref) {
		// TODO Auto-generated method stub
		PluginHelper.getInstance().logInfo("registerPlugin " + pluginID);
		nb++;

		List<Categories> catList = evidences.getCategories();
		for (Iterator iterator = catList.iterator(); iterator.hasNext();) {
			Categories categories = (Categories) iterator.next();
			if (categories.getName().equals("static")){
				PluginHelper.getInstance().logInfo("The plugin " + pluginID + "will be run for static analysis");
				staticAnalysisplugin.add(pluginID);
			}else if (categories.getName().equals("runtime")){
				PluginHelper.getInstance().logInfo("The plugin " + pluginID + "will be run for runtime analysis");
				runtimeAnalysisPlugin.add(pluginID);
			}
		}
		PluginHelper.getInstance().logInfo("nb = " + nb);

	}

	/* (non-Javadoc)
	 * @see com.thalesgroup.optet.twmanagement.OrchestrationInterface#staticAnalyse(int)
	 */
	@Override
	public void staticAnalyse(IProject project) {
		// TODO Auto-generated method stub
		staticInProgress = true;
		currentFinishedStaticAnalysisplugin.clear();
		List<IFile> srcPath = getJavaProjectSourceDirectories(project);


		PluginHelper.getInstance().logInfo("rrrrrrrrrrrrrrrrrrr " );
		ServiceReference<EventAdmin> ref = Activator.getContext().getServiceReference(EventAdmin.class);
		EventAdmin eventAdmin = Activator.getContext().getService(ref);
		PluginHelper.getInstance().logInfo("rrrrrrrrrrrrrrrrrrr " );
		Map<String,Object> properties = new HashMap<String, Object>();
		properties.put("DATA", new Date());
		properties.put("project", project);
		properties.put("sources", srcPath);
		PluginHelper.getInstance().logInfo("rrrrrrrrrrrrrrrrrrr " );


		Properties prop = new Properties();
		String projectID = null;
		String projectType = null;
		try {
			prop.load(project.getFile("/Optet/Optet.properties").getContents());
			projectID = prop.getProperty("svn.project.selected");
			projectType = prop.getProperty("project.type");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		List<Evidence> evidences = settings.getEvidences(projectID);
		properties.put("evidences", evidences);
		properties.put("projectType", projectType);

		Event event = new Event("viewcommunication/staticAnalyse", properties);
		eventAdmin.sendEvent(event);
		PluginHelper.getInstance().logInfo("rrrrrrrrrrrrrrrrrrr " );

	}

	@Override
	public void staticJMLAnalyse(IProject project) {
		// TODO Auto-generated method stub
		jmlInProgress = true;
		currentFinishedStaticAnalysisplugin.clear();
		List<IFile> srcPath = getJavaProjectSourceDirectories(project);


		PluginHelper.getInstance().logInfo("rrrrrrrrrrrrrrrrrrr " );
		ServiceReference<EventAdmin> ref = Activator.getContext().getServiceReference(EventAdmin.class);
		EventAdmin eventAdmin = Activator.getContext().getService(ref);
		PluginHelper.getInstance().logInfo("rrrrrrrrrrrrrrrrrrr " );
		Map<String,Object> properties = new HashMap<String, Object>();
		properties.put("DATA", new Date());
		properties.put("project", project);
		properties.put("sources", srcPath);
		PluginHelper.getInstance().logInfo("rrrrrrrrrrrrrrrrrrr " );


		Properties prop = new Properties();
		String projectID = null;
		String projectType = null;
		try {
			prop.load(project.getFile("/Optet/Optet.properties").getContents());
			projectID = prop.getProperty("svn.project.selected");
			projectType = prop.getProperty("project.type");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		List<Evidence> evidences = settings.getEvidences(projectID);
		properties.put("evidences", evidences);
		properties.put("projectType", projectType);

		Event event = new Event("viewcommunication/staticJMLAnalyse", properties);
		eventAdmin.sendEvent(event);
		PluginHelper.getInstance().logInfo("rrrrrrrrrrrrrrrrrrr " );

	}

	/* (non-Javadoc)
	 * @see com.thalesgroup.optet.twmanagement.OrchestrationInterface#runtimeAnalyse(int)
	 */
	@Override
	public void runtimeAnalyse(IProject project) {
		// TODO Auto-generated method stub
		runtimeInProgress = true;
		currentFinishedRuntimeAnalysisPlugin.clear();
		List<IFile> srcPath = getJavaProjectSourceDirectories(project);
		PluginHelper.getInstance().logInfo("rrrrrrrrrrrrrrrrrrr " );
		ServiceReference<EventAdmin> ref = Activator.getContext().getServiceReference(EventAdmin.class);
		EventAdmin eventAdmin = Activator.getContext().getService(ref);
		PluginHelper.getInstance().logInfo("rrrrrrrrrrrrrrrrrrr " );
		Map<String,Object> properties = new HashMap<String, Object>();

		properties.put("project", project);
		properties.put("sources", srcPath);
		//properties.put("selection", selection);
		PluginHelper.getInstance().logInfo("rrrrrrrrrrrrrrrrrrr " );

		Event event = new Event("viewcommunication/runtimeAnalyse", properties);
		eventAdmin.sendEvent(event);
		PluginHelper.getInstance().logInfo("rrrrrrrrrrrrrrrrrrr " );
	}

	/**
	 * return the list of file content into the resource
	 * @param files list of file to return
	 * @param resource the selection 
	 * @param extension return the file only with this extention
	 * @throws CoreException
	 */
	private static void listFiles(List<IFile> files, IResource resource, String extension) throws CoreException {
		// the resource is a folder

		//PluginHelper.getInstance().logInfo("resources " + resource.getFullPath());
		if (resource instanceof IFolder) {
			IResource[] res = ((IFolder)resource).members();
			for (int i = 0; i < res.length; i++) {
				listFiles(files, res[i], extension);
			}
		}
		else if (resource instanceof IFile) {
			if (((IFile)resource).getFileExtension() != null){
				if (((IFile)resource).getFileExtension().endsWith(extension)){
					files.add((IFile) resource);
				}
			}
		}
	}

	public static List<IFile> getJavaProjectSourceDirectories (IProject project) {
		ArrayList paths = new ArrayList();
		List<IFile> fileToAnalyse = new ArrayList<>();


		if (project.isOpen()){

			IJavaProject javaProject = JavaCore.create(project);
			IClasspathEntry[] classpathEntries = null;

			try {
				classpathEntries = javaProject.getResolvedClasspath(true);
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i = 0; i<classpathEntries.length;i++){
				IClasspathEntry entry = classpathEntries[i];

				if(entry.getContentKind() == IPackageFragmentRoot.K_SOURCE){
					IPath path = entry.getPath();


					PluginHelper.getInstance().logInfo(" entry.getPath() " +  entry.getPath());

					IFolder folder = project.getFolder(entry.getPath().toString().replaceFirst("/"+project.getName(), "")) ;
					PluginHelper.getInstance().logInfo(folder.getName() + "   " + folder.getFullPath());
					try {
						listFiles(fileToAnalyse, folder,"java");
					} catch (CoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//	                String srcPath = path.segments()[path.segmentCount()-1];
					//	                paths.add(srcPath);
				}

			}
		}

		return fileToAnalyse;


	}

	/* (non-Javadoc)
	 * @see com.thalesgroup.optet.twmanagement.OrchestrationInterface#staticAnalyseFinished(java.lang.String)
	 */
	@Override
	public void staticAnalyseFinished(String pluginID) {
		// TODO Auto-generated method stub
		PluginHelper.getInstance().logInfo("The plugin " + pluginID + " has finished his static analysis");
		currentFinishedStaticAnalysisplugin.add(pluginID);

		ServiceReference<EventAdmin> ref = Activator.getContext().getServiceReference(EventAdmin.class);
		EventAdmin eventAdmin = Activator.getContext().getService(ref);
		Map<String,Object> properties = new HashMap<String, Object>();
		properties.put("view", "com.thalesgroup.optet.devenv.views.OptetAuditView");

		Event event = new Event("viewdisplay/dispalyView", properties);

		eventAdmin.sendEvent(event);

		if (currentFinishedStaticAnalysisplugin.size() == staticAnalysisplugin.size()){

			staticInProgress = false;
			OptetDataModel.getInstance().computeRulesMetric();
			if (currentFinishedRuntimeAnalysisPlugin.size() == runtimeAnalysisPlugin.size()&& staticAndRuntime && !runtimeInProgress && !jmlInProgress){
				OptetDataModel.getInstance().computeMetric();
				staticAndRuntime = false;
				properties.put("view", "com.thalesgroup.optet.devenv.views.OptetMetricView");
				PluginHelper.getInstance().logInfo("the phase " + phase);
						properties.put("phase", phase);
				
				event = new Event("viewdisplay/dispalyView", properties);
				eventAdmin.sendEvent(event);
				phase=null;
			}

			PluginHelper.getInstance().logInfo("The static analysis is finished");


		}
	}

	@Override
	public void staticJMLAnalyseFinished(String pluginID) {
		jmlInProgress = false;
		
		Map<String,Object> properties = new HashMap<String, Object>();
		ServiceReference<EventAdmin> ref = Activator.getContext().getServiceReference(EventAdmin.class);
		EventAdmin eventAdmin = Activator.getContext().getService(ref);
		Event event = new Event("viewdisplay/dispalyView", properties);
		
		
		if (currentFinishedRuntimeAnalysisPlugin.size() == runtimeAnalysisPlugin.size() && staticAndRuntime && !staticInProgress && !runtimeInProgress){
			runtimeInProgress = false;
			OptetDataModel.getInstance().computeMetric();
			staticAndRuntime = false;
			properties.put("view", "com.thalesgroup.optet.devenv.views.OptetMetricView");
			PluginHelper.getInstance().logInfo("the phase " + phase);
			properties.put("phase", phase);
			event = new Event("viewdisplay/dispalyView", properties);
			eventAdmin.sendEvent(event);
			phase=null;
		}
	}

	/* (non-Javadoc)
	 * @see com.thalesgroup.optet.twmanagement.OrchestrationInterface#runtimeAnalyseFinished(java.lang.String)
	 */
	@Override
	public void runtimeAnalyseFinished(String pluginID) {
		// TODO Auto-generated method stub
		PluginHelper.getInstance().logInfo("The plugin " + pluginID + " has finished his runtime analysis");
		currentFinishedRuntimeAnalysisPlugin.add(pluginID);
		if (currentFinishedRuntimeAnalysisPlugin.size() == runtimeAnalysisPlugin.size())
		{
			runtimeInProgress = false;
			ServiceReference<EventAdmin> ref = Activator.getContext().getServiceReference(EventAdmin.class);
			EventAdmin eventAdmin = Activator.getContext().getService(ref);
			Map<String,Object> properties = new HashMap<String, Object>();
			properties.put("view", "com.thalesgroup.optet.devenv.views.OptetTestCoverageView");

			Event event = new Event("viewdisplay/dispalyView", properties);

			eventAdmin.sendEvent(event);


			PluginHelper.getInstance().logInfo("The runtime analysis is finished");

			if (currentFinishedRuntimeAnalysisPlugin.size() == runtimeAnalysisPlugin.size() && staticAndRuntime && !staticInProgress && !jmlInProgress){
				runtimeInProgress = false;
				OptetDataModel.getInstance().computeMetric();
				staticAndRuntime = false;
				properties.put("view", "com.thalesgroup.optet.devenv.views.OptetMetricView");
				PluginHelper.getInstance().logInfo("the phase " + phase);
				properties.put("phase", phase);
				event = new Event("viewdisplay/dispalyView", properties);
				eventAdmin.sendEvent(event);
				phase=null;
			}


		}
	}

	/* (non-Javadoc)
	 * @see com.thalesgroup.optet.twmanagement.OrchestrationInterface#staticAndRuntimeAnalyse(org.eclipse.core.resources.IProject)
	 */
	@Override
	public void staticAndRuntimeAnalyse(IProject project, String phase) {
		// TODO Auto-generated method stub
		this.phase = phase;
		staticAndRuntime = true;
		this.staticAnalyse(project);
		this.runtimeAnalyse(project);
		this.staticJMLAnalyse(project);
		
//	    try {
//			if (project.hasNature(JavaCore.NATURE_ID)) {
//				PluginHelper.getInstance().logInfo("it's a java project");
//				IJavaProject javaProject = JavaCore.create(project);
//				if (javaProject!=null)
//				{
//					OptetDataModel.getInstance().cleanMetric();
//					ComputeJMLInput compute = new ComputeJMLInput(javaProject, null);
//					compute.run();
//				}
//			}else{
//				PluginHelper.getInstance().logInfo("it's not a java project");
//			}
//		} catch (CoreException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}


	} 


}

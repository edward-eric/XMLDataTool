package org.data.xml.support.uiengine;

import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public abstract class UIEngine implements Runnable{
	
	protected StatefulJTabbedPane _screenTabsPane;
	
	public UIEngine() {
		Thread shutdownHook = new Thread(){
			public void run() {
				
			}
		};
		Runtime.getRuntime().addShutdownHook(shutdownHook);
	}
	
	public void initialize(HashMap<String, Object> props)
	{
		createApplicationSkeleton("test");
	}
	
	private void createApplicationSkeleton (String label){
		JFrame appFrame = new JFrame(label);
		
		JPanel appPanel = new JPanel(new BorderLayout());
		_screenTabsPane = new StatefulJTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		
	}
	
	
	private static class StatefulJTabbedPane extends JTabbedPane {
		
		private int _lastSelectedIndex = -1;
		
		public StatefulJTabbedPane(int tabPlacement, int tabLayoutPolicy){
			super(tabPlacement, tabLayoutPolicy);
		}
		
		public void setSelectedIndex(int anIndex){
			_lastSelectedIndex = getSelectedIndex();
			super.setSelectedIndex(anIndex);
		}
		
		public int getLastSelectedIndex() {
			return _lastSelectedIndex;
		}
		
		public void setLastSelectedIndex(int lastSelectedIndex){
			_lastSelectedIndex = lastSelectedIndex;
		}
	}

}

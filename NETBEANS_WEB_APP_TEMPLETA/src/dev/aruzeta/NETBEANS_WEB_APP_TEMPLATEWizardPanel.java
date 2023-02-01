/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aruzeta;

import java.awt.Component;
import java.util.HashSet;
import java.util.Set;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.WizardValidationException;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;

/**
 * Panel just asking for basic info.
 */
public class NETBEANS_WEB_APP_TEMPLATEWizardPanel implements WizardDescriptor.Panel,
  WizardDescriptor.ValidatingPanel, WizardDescriptor.FinishablePanel {

  private WizardDescriptor wizardDescriptor;
  private NETBEANS_WEB_APP_TEMPLATEPanelVisual component;

  public NETBEANS_WEB_APP_TEMPLATEWizardPanel() {
  }

  public Component getComponent() {
    if (component == null) {
      component = new NETBEANS_WEB_APP_TEMPLATEPanelVisual(this);
      component.setName(NbBundle.getMessage(NETBEANS_WEB_APP_TEMPLATEWizardPanel.class, "LBL_CreateProjectStep"));
    }
    return component;
  }

  public HelpCtx getHelp() {
    return new HelpCtx(NETBEANS_WEB_APP_TEMPLATEWizardPanel.class);
  }

  public boolean isValid() {
    getComponent();
    return component.valid(wizardDescriptor);
  }

  private final Set<ChangeListener> listeners = new HashSet<ChangeListener>(1); // or can use ChangeSupport in NB 6.0

  public final void addChangeListener(ChangeListener l) {
    synchronized (listeners) {
      listeners.add(l);
    }
  }

  public final void removeChangeListener(ChangeListener l) {
    synchronized (listeners) {
      listeners.remove(l);
    }
  }

  protected final void fireChangeEvent() {
    Set<ChangeListener> ls;
    synchronized (listeners) {
      ls = new HashSet<ChangeListener>(listeners);
    }
    ChangeEvent ev = new ChangeEvent(this);
    for (ChangeListener l : ls) {
      l.stateChanged(ev);
    }
  }

  public void readSettings(Object settings) {
    wizardDescriptor = (WizardDescriptor) settings;
    component.read(wizardDescriptor);
  }

  public void storeSettings(Object settings) {
    WizardDescriptor d = (WizardDescriptor) settings;
    component.store(d);
  }

  public boolean isFinishPanel() {
    return true;
  }

  public void validate() throws WizardValidationException {
    getComponent();
    component.validate(wizardDescriptor);
  }

}
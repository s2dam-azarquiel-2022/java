package model;

public class Platforms {
  private boolean windows;
  private boolean linux;
  private boolean mac;

  public boolean isWindows() {
    return windows;
  }
  public void setWindows(boolean windows) {
    this.windows = windows;
  }
  public boolean isLinux() {
    return linux;
  }
  public void setLinux(boolean linux) {
    this.linux = linux;
  }
  public boolean isMac() {
    return mac;
  }
  public void setMac(boolean mac) {
    this.mac = mac;
  }
}

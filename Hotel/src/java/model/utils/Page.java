package model.utils;

import controller.servlet.Root;
import controller.utils.ServletConfig.ReqVars;
import view.PageUtils;

public final class Page {
  private static int currID = 0;
  public final String file;
  public final String displayName;
  public final String href;
  public final int id;

  public Page(String file, String displayName) {
    this.id = currID++;
    this.href = String.format(
      "%s%s&%s=%d",
      PageUtils.path,
      Root.opt(Root.Option.CHANGE_PAGE),
      ReqVars.PAGE.name(),
      this.id
    );
    this.file = file;
    this.displayName = displayName;
  }
}

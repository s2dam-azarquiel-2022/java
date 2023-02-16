package model.utils;

import controller.servlet.Root;
import controller.utils.ServletConfig.ReqVars;
import view.PageUtils;

public final class Page {
  public final String displayName;
  public final String href;

  public Page(
    String displayName,
    Short id
  ) {
    this.href = String.format(
      "%s%s&%s=%d",
      PageUtils.path,
      Root.opt(Root.Option.FILTER),
      ReqVars.FILTER.name(),
      id
    );
    // this.file = file;
    this.displayName = displayName;
  }
}

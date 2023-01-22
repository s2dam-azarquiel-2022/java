<%@page import="view.PageUtils"%>
<%@page import="controller.servlet.ServletConfig.ReqVars"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<div <%=PageUtils.mainModalSetup("loginModal")%>>
  <div class="modal-dialog">
    <div <%=PageUtils.mainModalContentSetup%>>
      <div class="modal-header">
        <h5 class="modal-title">Login | Register</h5>
      </div>
      <div class="modal-body">
        <form id="loginForm" action="/<%=PageUtils.pageName%>/Login" method="post" class="d-grid gap-3">
          <div>
            <label <%=PageUtils.mainLabelSetup(ReqVars.USERNAME)%>>DNI</label>
            <input <%=PageUtils.mainInputSetup(ReqVars.USERNAME)%> />
          </div>
          <div>
            <label <%=PageUtils.mainLabelSetup(ReqVars.PASSWORD)%>>Password</label>
            <input <%=PageUtils.mainInputSetup(ReqVars.PASSWORD, "password")%> />
          </div>
          <div class="d-flex justify-content-between">
            <button class="btn btn-success" type="submit">Login | Register</button>
            <button <%=PageUtils.mainModalCloseBtnSetup%>>Cancel</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
          
<script>
  const cyrb53 = (str, seed = 0) => {
    let h1 = 0xdeadbeef ^ seed;
    let h2 = 0x41c6ce57 ^ seed;
    for (let i = 0, ch; i < str.length; i++) {
      ch = str.charCodeAt(i);
      h1 = Math.imul(h1 ^ ch, 2654435761);
      h2 = Math.imul(h2 ^ ch, 1597334677);
    }
    h1 = Math.imul(h1 ^ (h1 >>> 16), 2246822507) ^ Math.imul(h2 ^ (h2 >>> 13), 3266489909);
    h2 = Math.imul(h2 ^ (h2 >>> 16), 2246822507) ^ Math.imul(h1 ^ (h1 >>> 13), 3266489909);
    return 4294967296 * (2097151 & h2) + (h1 >>> 0);
  };

  $('#loginForm').on("submit", function() {
    console.log(this);
    let password = $('input[name="<%=ReqVars.PASSWORD.name()%>"]', this);
    password.val(cyrb53(password.val()));
    return true;
  });
</script>
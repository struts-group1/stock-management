package action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.json.annotations.JSON;
import org.hibernate.validator.Valid;

import com.opensymphony.xwork2.ActionSupport;

import constant.Page;
import dao.SupplierDao;
import dao.impl.SupplierDaoImpl;
import model.Supplier;
import util.WebUtil;

@ParentPackage("default")
@Namespace("/supplier")
@ResultPath("/")
public class SupplierAction extends ActionSupport implements IAction {

	private static final long serialVersionUID = 8493665757717540594L;

	private SupplierDao supplierDao = new SupplierDaoImpl();
	private Boolean existsGoods;

	@Valid
	private Supplier supplierBean = new Supplier();

	@Action(value = "list", results = @Result(name = SUCCESS, location = Page.SUPPLIER_LIST_PAGE))
	public String list() {
		return SUCCESS;
	}

	@Action(value = "add", results = @Result(name = SUCCESS, location = Page.SUPPLIER_FORM_PAGE))
	public String add() {
		return SUCCESS;
	}

	@Action(value = "edit", results = @Result(name = SUCCESS, location = Page.SUPPLIER_FORM_PAGE))
	public String edit() {
		Integer id = Integer.parseInt(WebUtil.getHttpServletRequest().getParameter("id"));
		supplierBean = supplierDao.findById(id);
		return SUCCESS;
	}

	@Action(value = "delete", results = @Result(name = SUCCESS, location = "list", type = "redirect"))
	public String delete() {
		Integer id = Integer.parseInt(WebUtil.getHttpServletRequest().getParameter("id"));
		supplierBean = supplierDao.findById(id);
		supplierDao.delete(supplierBean);
		return SUCCESS;
	}

	@Action(value = "save", interceptorRefs = @InterceptorRef("defaultStackHibernateStrutsValidation"), results = {
			@Result(name = SUCCESS, location = "list", type = "redirect"),
			@Result(name = INPUT, location = Page.SUPPLIER_FORM_PAGE) })
	public String save() {
		// add
		Integer id = supplierBean.getId();
		if (id == null) {
			if (supplierDao.findByName(supplierBean.getName()) != null) {
				addFieldError("supplierBean.name", "Tên nhà cung cấp đã tồn tại!");
				return INPUT;
			}
		} else { // update
			String formName = supplierBean.getName();
			String currentName = supplierDao.findNameById(id);
			if (!formName.equalsIgnoreCase(currentName)) { // Không trùng với tên hiện tại
				if (supplierDao.isDuplicateAnotherName(formName, id)) {
					addFieldError("supplierBean.name", "Tên nhà cung cấp đã tồn tại!");
					return INPUT;
				}
			}
		}
		supplierDao.saveOrUpdate(supplierBean);
		return SUCCESS;
	}

	@Action(value = "existsSupplierCode", results = @Result(name = SUCCESS, type = "json"))
	public String existsGoodsCode() {
		String supplierCode = WebUtil.getHttpServletRequest().getParameter("supplierCode");
		supplierBean = supplierDao.findByCode(supplierCode);
		return SUCCESS;
	}

	@Action(value = "existsSupplierId", results = @Result(name = SUCCESS, type = "json"))
	public String existsGoodsId() {
		Integer supplierId = Integer.parseInt(WebUtil.getHttpServletRequest().getParameter("supplierId"));
		existsGoods = supplierDao.existsGoods(supplierId);
		return SUCCESS;
	}

	public Boolean getExistsGoods() {
		return existsGoods;
	}

	public Supplier getSupplierBean() {
		return supplierBean;
	}

	public void setSupplierBean(Supplier supplierBean) {
		this.supplierBean = supplierBean;
	}

	public List<Supplier> getSupplierList() {
		String find = WebUtil.getHttpServletRequest().getParameter("find");
		if (find != null) {
			return supplierDao.findAll(find, true);
		} else {
			return supplierDao.findAll(true);
		}
	}

	@JSON(serialize = false)
	public Boolean getDefaultActiveValue() {
		if (supplierBean.getActive() == null) {
			supplierBean.setActive(true);
		}
		return supplierBean.getActive();
	}
	
	public Map<Boolean, String> getActives() {
		HashMap<Boolean, String> actives = new HashMap<>();
		actives.put(true, "Hoạt động");
		actives.put(false, "Tạm dừng");
		return actives;
	}

	@Override
	public void validate() {
		System.err.println("Validate Supplier.....");
	}

}

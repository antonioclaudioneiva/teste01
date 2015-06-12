package grupo.teste01.persistence;

import grupo.teste01.entity.Bookmark;

import java.util.List;

import javax.inject.Scope;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class BookmarkDAO extends JPACrud<Bookmark, Long> {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return super.getEntityManager();
	}

	public List<Bookmark> find(String filter) {
		StringBuffer ql = new StringBuffer();
		ql.append("  from Bookmark b ");
		ql.append(" where lower(b.description) like :description ");
		ql.append("    or lower(b.link) like :link ");

		TypedQuery<Bookmark> query = getEntityManager().createQuery(ql.toString(), Bookmark.class);
		query.setParameter("description", "%" + filter.toLowerCase() + "%");
		query.setParameter("link", "%" + filter.toLowerCase() + "%");

		return query.getResultList();
	}
}

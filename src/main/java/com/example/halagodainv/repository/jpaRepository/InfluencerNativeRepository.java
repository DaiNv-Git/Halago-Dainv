package com.example.halagodainv.repository.jpaRepository;

import com.example.halagodainv.response.Influencer.InfluencerSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class InfluencerNativeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Page<InfluencerSearchDTO> searchInfluencers(String name, String industryName, boolean fb, boolean youtobe, boolean titok, boolean instagram, Pageable pageable) {
        try {
            String sqlQuery = "SELECT a.id, a.name, a.description, " +
                    "GROUP_CONCAT(b.industry_name SEPARATOR ', ') AS industries, " +
                    "CASE WHEN a.fb = 1 THEN 'true' ELSE 'false' END AS fb, " +
                    "CASE WHEN a.youtobe = 1 THEN 'true' ELSE 'false' END AS youtobe, " +
                    "CASE WHEN a.titok = 1 THEN 'true' ELSE 'false' END AS titok, " +
                    "CASE WHEN a.instagram = 1 THEN 'true' ELSE 'false' END AS instagram, " +
                    "a.phone " +
                    "FROM influencer AS a " +
                    "JOIN industry AS b ON FIND_IN_SET(b.id, a.industry_id) > 0 " +
                    "WHERE (:name IS NULL OR a.name LIKE :name) " +
                    "AND (:industryName IS NULL OR a.industry_id LIKE :industryName) " +
                    "AND (:fb IS NULL OR a.fb = :fb) " +
                    "AND (:youtobe IS NULL OR a.youtobe = :youtobe) " +
                    "AND (:titok IS NULL OR a.titok = :titok) " +
                    "AND (:instagram IS NULL OR a.instagram = :instagram) " +
                    "GROUP BY a.id, a.name, a.description, a.phone, a.fb, a.youtobe, a.titok, a.instagram";
            Query query = entityManager.createNativeQuery(sqlQuery, "InfluencerDTO");
            // Gán giá trị cho tham số name
            if (name != null) {
                query.setParameter("name", "%" + name + "%");
            } else {
                query.setParameter("name", null);
            }
            // Gán giá trị cho tham số industryName
            if (industryName != null) {
                query.setParameter("industryName", "%" + industryName + "%");
            } else {
                query.setParameter("industryName", null);
            }
            // Gán giá trị cho tham số fb
            if (fb) {
                query.setParameter("fb", 1);
            } else {
                query.setParameter("fb", null);
            }
            // Gán giá trị cho tham số youtobe
            if (youtobe) {
                query.setParameter("youtobe", 1);
            } else {
                query.setParameter("youtobe", null);
            }
            // Gán giá trị cho tham số titok
            if (titok) {
                query.setParameter("titok", 1);
            } else {
                query.setParameter("titok", null);
            }
            // Gán giá trị cho tham số instagram
            if (instagram) {
                query.setParameter("instagram", 1);
            } else {
                query.setParameter("instagram", null);
            }
            List<InfluencerSearchDTO> resultList = query.getResultList();
            // Tính tổng số bản ghi
            int totalRecords = resultList.size();
            // Tạo đối tượng Page để trả về
            Page<InfluencerSearchDTO> pageResult = new PageImpl<>(resultList, pageable, totalRecords);
            return pageResult;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}

package com.example.halagodainv.repository.jpaRepository;

import com.example.halagodainv.response.Influencer.InfluencerSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InfluencerNativeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Page<InfluencerSearchDTO> searchInfluencers(String name, String industryName, Boolean fb, Boolean youtobe, Boolean titok, Boolean instagram,
                                                       Integer sex, Integer cityId, String birthday, Double expense, Pageable pageable) {
        try {
            String sqlQuery = "SELECT a.id, a.name, a.description, " +
                    "GROUP_CONCAT(b.industry_name SEPARATOR ', ') AS industries, " +
                    "CASE WHEN a.fb = 1 THEN 'true' ELSE 'false' END AS fb, " +
                    "CASE WHEN a.youtobe = 1 THEN 'true' ELSE 'false' END AS youtobe, " +
                    "CASE WHEN a.titok = 1 THEN 'true' ELSE 'false' END AS titok, " +
                    "CASE WHEN a.instagram = 1 THEN 'true' ELSE 'false' END AS instagram, " +
                    "a.phone, " +
                    "CASE WHEN a.sex = 0 THEN 'Nam' ELSE 'Nữ' END AS sexValue, " +
                    "s.expense " +
                    "FROM influencer AS a " +
                    "LEFT JOIN social_network_influencer AS s ON a.id = s.influencerID " +
                    "LEFT JOIN industry AS b ON FIND_IN_SET(b.id, a.industry_id) > 0 ";
            // Xử lý tìm kiếm theo name (nếu có giá trị)
            if (name != null && !name.isEmpty()) {
                sqlQuery += "WHERE a.name LIKE :name ";
            }

            // Xử lý tìm kiếm theo industryName (nếu có giá trị)
            if (industryName != null && !industryName.isEmpty()) {
                if (name != null && !name.isEmpty()) {
                    sqlQuery += "AND b.industry_name LIKE :industryName ";
                } else {
                    sqlQuery += "WHERE b.industry_name LIKE :industryName ";
                }
            }

            // Xử lý tìm kiếm theo fb (nếu có giá trị)
            if (fb != null && fb ==true) {
                if (name != null && !name.isEmpty() || industryName != null && !industryName.isEmpty()) {
                    sqlQuery += "AND a.fb = :fb ";
                } else {
                    sqlQuery += "WHERE a.fb = :fb ";
                }
            }

            // Xử lý tìm kiếm theo youtobe (nếu có giá trị)
            if (youtobe != null && youtobe ==true) {
                if (name != null && !name.isEmpty() || industryName != null && !industryName.isEmpty() || fb != null) {
                    sqlQuery += "AND a.youtobe = :youtobe ";
                } else {
                    sqlQuery += "WHERE a.youtobe = :youtobe ";
                }
            }

            // Xử lý tìm kiếm theo titok (nếu có giá trị)
            if (titok != null&& titok ==true) {
                if (name != null && !name.isEmpty() || industryName != null && !industryName.isEmpty() || fb != null || youtobe != null) {
                    sqlQuery += "AND a.titok = :titok ";
                } else {
                    sqlQuery += "WHERE a.titok = :titok ";
                }
            }

            // Xử lý tìm kiếm theo instagram (nếu có giá trị)
            if (instagram != null && instagram ==true) {
                if (name != null && !name.isEmpty() || industryName != null && !industryName.isEmpty() || fb != null || youtobe != null || titok != null) {
                    sqlQuery += "AND a.instagram = :instagram ";
                } else {
                    sqlQuery += "WHERE a.instagram = :instagram ";
                }
            }

            // Xử lý tìm kiếm theo sex (nếu có giá trị)
            if (sex != null) {
                if (name != null && !name.isEmpty() || industryName != null && !industryName.isEmpty() || fb != null || youtobe != null || titok != null || instagram != null) {
                    sqlQuery += "AND a.sex = :sex ";
                } else {
                    sqlQuery += "WHERE a.sex = :sex ";
                }
            }

            // Xử lý tìm kiếm theo cityId (nếu có giá trị)
            if (cityId != null) {
                if (name != null && !name.isEmpty() || industryName != null && !industryName.isEmpty() || fb != null || youtobe != null || titok != null || instagram != null || sex != null) {
                    sqlQuery += "AND a.city = :cityId ";
                } else {
                    sqlQuery += "WHERE a.city = :cityId ";
                }
            }

            // Xử lý tìm kiếm theo birthday (nếu có giá trị)
            if (birthday != null) {
                if (name != null && !name.isEmpty() || industryName != null && !industryName.isEmpty() || fb != null || youtobe != null || titok != null || instagram != null || sex != null || cityId != null) {
                    sqlQuery += "AND a.birthday = :birthday ";
                } else {
                    sqlQuery += "WHERE a.birthday = :birthday ";
                }
            }

            // Xử lý tìm kiếm theo expense (nếu có giá trị)
            if (expense != null) {
                if (name != null && !name.isEmpty() || industryName != null && !industryName.isEmpty() || fb != null || youtobe != null || titok != null || instagram != null || sex != null || cityId != null || birthday != null) {
                    sqlQuery += "AND s.expense = :expense ";
                } else {
                    sqlQuery += "WHERE s.expense = :expense ";
                }
            }

            sqlQuery += "GROUP BY a.id, a.name, a.description, a.phone, a.sex, s.expense, a.fb, a.youtobe, a.titok, a.instagram";

            Query query = entityManager.createNativeQuery(sqlQuery);

            // Gán giá trị cho các tham số tìm kiếm
            if (name != null && !name.isEmpty()) {
                query.setParameter("name", "%" + name + "%");
            }

            if (industryName != null && !industryName.isEmpty()) {
                query.setParameter("industryName", "%" + industryName + "%");
            }

            if (fb != null && fb ==true) {
                query.setParameter("fb", fb);
            }

            if (youtobe != null && youtobe ==true) {
                query.setParameter("youtobe", youtobe);
            }

            if (titok != null && titok ==true) {
                query.setParameter("titok", titok);
            }

            if (instagram != null && instagram ==true) {
                query.setParameter("instagram", instagram);
            }

            if (sex != null) {
                query.setParameter("sex", sex);
            }

            if (cityId != null) {
                query.setParameter("cityId", cityId);
            }

            if (birthday != null) {
                query.setParameter("birthday", birthday);
            }

            if (expense != null) {
                query.setParameter("expense", expense);
            }
            // Thực hiện truy vấn và lấy kết quả
            List<Object[]> resultList = query.getResultList();
            List<InfluencerSearchDTO> finalResultList = new ArrayList<>();
            for (Object[] result : resultList) {
                int id = (int) result[0];
                String influencerName = (String) result[1];
                String description = (String) result[2];
                String industries = (String) result[3];
                boolean fbValue = "true".equalsIgnoreCase((String) result[4]);
                boolean youtobeValue = "true".equalsIgnoreCase((String) result[5]);
                boolean titokValue = "true".equalsIgnoreCase((String) result[6]);
                boolean instagramValue = "true".equalsIgnoreCase((String) result[7]);
                String phone = (String) result[8];
                String sexValue = (String) result[9];
                Double expenseValue = (Double) result[10];

                InfluencerSearchDTO dto = new InfluencerSearchDTO(id, influencerName, description, industries, fbValue, youtobeValue, titokValue, instagramValue, phone, sexValue, expenseValue);
                finalResultList.add(dto);
            }

// Tính tổng số bản ghi
            int totalRecords = finalResultList.size();

// Xử lý phân trang và trả về đối tượng Page
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), totalRecords);
            List<InfluencerSearchDTO> pagedResult = finalResultList.subList(start, end);
            Page<InfluencerSearchDTO> pageResult = new PageImpl<>(pagedResult, pageable, totalRecords);

            return pageResult;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}

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
                    "a.phone, a.sex, s.expense " +
                    "FROM influencer AS a " +
                    "LEFT JOIN social_network_influencer AS s ON a.id = s.influencerID " +
                    "JOIN industry AS b ON FIND_IN_SET(b.id, a.industry_id) > 0 ";

            // Xử lý tìm kiếm theo industryName (nếu có)
            if (industryName != null && !industryName.isEmpty()) {
                sqlQuery += "WHERE b.industry_name LIKE :industryName ";
            }

            // Xử lý tìm kiếm theo sex (nếu có)
            if (sex != null) {
                sqlQuery += "AND a.sex = :sex ";
            }

            // Xử lý tìm kiếm theo cityId (nếu có)
            if (cityId != null) {
                sqlQuery += "AND a.city = :cityId ";
            }

            // Xử lý tìm kiếm theo birthday (nếu có)
            if (birthday != null) {
                sqlQuery += "AND a.birthday = :birthday ";
            }

            // Xử lý tìm kiếm theo expense (nếu có)
            if (expense != null) {
                sqlQuery += "AND s.expense = :expense ";
            }

            sqlQuery += "GROUP BY a.id, a.name, a.description, a.phone, a.sex, s.expense, a.fb, a.youtobe, a.titok, a.instagram";

            Query query = entityManager.createNativeQuery(sqlQuery);

            if (industryName != null && !industryName.isEmpty()) {
                query.setParameter("industryName", "%" + industryName + "%");
            }

            if (sex != null) {
                sqlQuery += "AND a.sex = :sex ";
            }


            if (cityId != null) {
                sqlQuery += "AND a.city = :cityId ";
            }


            if (birthday != null) {
                sqlQuery += "AND a.birthday = :birthday ";
            }

            if (expense != null) {
                sqlQuery += "AND s.expense = :expense ";
            }

            // Thực hiện truy vấn và lấy kết quả
            List<Object[]> resultList = query.getResultList();

            // Xử lý kết quả trả về để chia các giá trị industry_name thành các hàng riêng biệt
            List<InfluencerSearchDTO> finalResultList = new ArrayList<>();
            for (Object[] result : resultList) {
                Integer id = (Integer) result[0];
                String influencerName = (String) result[1];
                String description = (String) result[2];
                Boolean fbValue = (Boolean) result[3];
                Boolean youtobeValue = (Boolean) result[4];
                Boolean titokValue = (Boolean) result[5];
                Boolean instagramValue = (Boolean) result[6];
                String phone = (String) result[7];
                String sexValue = (String) result[8];
                String city = (String) result[9];
                Double expenseValue = (Double) result[10];

                // Tách các giá trị industry_name thành các hàng riêng biệt
                String industries = (String) result[11];
                if (industries != null && !industries.isEmpty()) {
                    String[] industryNames = industries.split(", ");
                    for (String industry : industryNames) {
                        InfluencerSearchDTO dto = new InfluencerSearchDTO(id, influencerName, description, industry, fbValue, youtobeValue, titokValue, instagramValue, phone, sexValue, city, expenseValue);
                        finalResultList.add(dto);
                    }
                } else {
                    // Không có giá trị industry_name, chỉ thêm một hàng duy nhất vào kết quả
                    InfluencerSearchDTO dto = new InfluencerSearchDTO(id, influencerName, description, null, fbValue, youtobeValue, titokValue, instagramValue, phone, sexValue, city, expenseValue);
                    finalResultList.add(dto);
                }
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

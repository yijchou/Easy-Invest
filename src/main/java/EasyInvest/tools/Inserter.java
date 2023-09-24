package EasyInvest.tools;

import EasyInvest.dal.*;
import EasyInvest.model.*;
import EasyInvest.model.DailyStockRecommendation.PositionType;
import EasyInvest.model.Users.CompetencyLevel;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Inserter {
	public static void main(String[] args) throws SQLException, ParseException {
		// DAO instances.
		AdminDao adminDao = AdminDao.getInstance();
		UsersDao usersDao = UsersDao.getInstance();
		MembershipUsersDao membershipUsersDao = MembershipUsersDao.getInstance();
		LikesDao likesDao = LikesDao.getInstance();
		RepostDao repostDao = RepostDao.getInstance();
		CreditCardsDao creditCardsDao = CreditCardsDao.getInstance();
		StrategyPostDao strategyPostDao = StrategyPostDao.getInstance();
		ReviewsDao reviewsDao = ReviewsDao.getInstance();
		EducationalMaterialsDao educationalMaterialsDao = EducationalMaterialsDao.getInstance();
		MembershipSubscriptionDao membershipSubscriptionDao = MembershipSubscriptionDao.getInstance();
		// StockRecommendation instance
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date1 = formatter.parse("2022-10-07 00:00:00");
		Date date2 = formatter.parse("2012-01-10 00:00:00");
		

		Date date = new Date();
		
		//some dates
		Date date3 = formatter.parse("2020-01-02 00:00:00");
		Date date4 = formatter.parse("2022-11-15 00:00:15");

		// Users instance
		Users user1 = new Users("user1","0000", date, true,"Clarissa","Fritz","a@gmail.com","309-840-8213",Users.CompetencyLevel.ADVANCED);
		Users user2 = new Users("user2","0000", date, false,"Deborah","Pittman","a@gmail.com","309-840-6832",Users.CompetencyLevel.BEGINNER);
		Users user3 = new Users("user3","0000", date, true,"Rebecca","Gonzales","a@gmail.com","217-646-0813",Users.CompetencyLevel.INTERMEDIATE);
		Users user4 = new Users("user4","0000", date, false,"Deborah","Pittman","a@gmail.com","309-840-6832",Users.CompetencyLevel.BEGINNER);
		Users user5 = new Users("user5","0000", date, true,"Rebecca","Gonzales","a@gmail.com","217-646-0813",Users.CompetencyLevel.INTERMEDIATE);
		Users user7 = new Users("INTERMEDIATE","0000", date, true,"Jill","Wang","a@gmail.com","217-646-0813",Users.CompetencyLevel.BEGINNER);
		
		//create
		user1 = usersDao.create(user1);
		user2 = usersDao.create(user2);
		user3 = usersDao.create(user3);
		user4 = usersDao.create(user4);
		user5 = usersDao.create(user5);
		user7 = usersDao.create(user7);
		
		//update password
		user1 = usersDao.updatePassword(user1, "1111");
		//delete user
		//usersDao.delete(user3);
		
		//get user by CompetencyLevel (should be a list?  --> refer to READ
		Users c1 = usersDao.getUserFromCompetencyLevel(Users.CompetencyLevel.ADVANCED);
		
		//get user by Date --> refer to READ
		List<Users> d1 = usersDao.getUsersFromCreateDate(date);
		

		// Administer instance
		Admin admin1 = new Admin("admin1", "11111", date, true, "Anna", "Zhang", "annaz@easyinvest.com", "206-006-7796", CompetencyLevel.EXPERT, date);
		admin1 = adminDao.create(admin1);
		Admin admin2 = new Admin("admin2", "22222", date, true, "Julia", "Huang", "juliah@easyinvest.com", "206-376-5515", CompetencyLevel.ADVANCED, date);
		admin2 = adminDao.create(admin2);
		Admin admin3 = new Admin("BEGINNER", "22222", date, true, "Sherry", "Huang", "juliah@easyinvest.com", "206-376-5515", CompetencyLevel.ADVANCED, date);
		admin3 = adminDao.create(admin3);
		
		
		// membership users instance		
		MembershipUsers membershipUser1 = new MembershipUsers("member1","0000", date, true,"Clarissa","Fritz",
				"a@gmail.com","309-840-8213",Users.CompetencyLevel.ADVANCED,123.12);
		membershipUser1 = membershipUsersDao.create(membershipUser1);
		MembershipUsers membershipUser2 = new MembershipUsers("member2","0000", date, true,"Rebecca","Gonzales",
				"a@gmail.com","217-646-0813",Users.CompetencyLevel.INTERMEDIATE,200.08);
		membershipUser2 = membershipUsersDao.create(membershipUser2);
		//update password
		membershipUser2 = membershipUsersDao.updatePassword(membershipUser2, "2222");
		//delete user
		//membershipUsersDao.delete(membershipUser1);

		Users u1 = usersDao.getUserFromCompetencyLevel(Users.CompetencyLevel.ADVANCED);
		List<Users> uList1 = usersDao.getUsersFromCreateDate(date);
		System.out.format("Reading user: %s,%s,%s,%s,%s,%s,%s   \n",
				u1.getUserName(),u1.getCreated(),u1.isMember(),
				u1.getFirstName(), u1.getLastName(), u1.getEmail(),u1.getPhone(),
				u1.getCompetencyLevel().name());
		for(Users u : uList1) {
			System.out.format("Looping users: u:%s f:%s l:%s \n",
					u1.getUserName(),u1.getCreated(),u1.isMember(),
					u1.getFirstName(), u1.getLastName(), u1.getEmail(),u1.getPhone(),
					u1.getCompetencyLevel().name());
		}
		// get 
		MembershipUsers mu1 = membershipUsersDao.getMembershipUserFromUserName("member1");
		List<MembershipUsers> muList1 = membershipUsersDao.getMembershipUsersFromUserName("member1");
		System.out.format("Reading membership user: %s,%s,%s,%s,%s,%s,%s,%s,%s",
				mu1.getUserName(),mu1.getCreated(),mu1.isMember(),
				mu1.getFirstName(), mu1.getLastName(), mu1.getEmail(),mu1.getPhone(),
				mu1.getCompetencyLevel().name(),mu1.getRevenue());
		
		for(MembershipUsers bu : muList1) {
			System.out.format("Looping blog users:  %s,%s,%s,%s,%s,%s,%s,%s,%s \n",
					mu1.getUserName(),mu1.getCreated(),mu1.isMember(),
					mu1.getFirstName(), mu1.getLastName(), mu1.getEmail(),mu1.getPhone(),
					mu1.getCompetencyLevel().name(),mu1.getRevenue());
		}

		// membership sub
//		MembershipSubscription memberSub = new MembershipSubscription(membershipUser1, 4.99, date);
//		memberSub = membershipSubscriptionDao.create(memberSub);
//		List <MembershipSubscription> m1 = membershipSubscriptionDao.getMembershipSubscriptionByUserName("member1");
//		for(MembershipSubscription m : m1) {
//		       System.out.format("Reading MembershipSubscription: %s %s %s %s \n",
//	           m.getTransactionId(), m.getUser().getUserName(), m.getSubscriptionPrice(), m.getStartDate());
//	}
//
//		membershipSubscriptionDao.updateSubscriptionPrice(memberSub, 8.99);

		// strategy post instances
		StrategyPost strategyPost1 = new StrategyPost("Increase Market Awaresness", "Pay attention to CPI", date, "member1", true, 17);
		strategyPost1 = strategyPostDao.create(strategyPost1);
		
		StrategyPost strategyPost2 = new StrategyPost("Set Smart Goals", "TRack meaningful metrics such as company revenue", date, "member1", true, 25);
		strategyPost2 = strategyPostDao.create(strategyPost2);
		
		StrategyPost strategyPost3 = new StrategyPost("Balance is everything", "Balance your investment industries and risks", date, "member2", true, 56);
		strategyPost3 = strategyPostDao.create(strategyPost3);
		
		
		StrategyPost post1 = strategyPostDao.getStrategyPostById(strategyPost1.getPostId());
	    System.out.format("Reading StrategyPost: %s %s %s %s %s %s \n",
	    post1.getPostId(), post1.getTitle(), post1.getContent(), post1.getCreated(), post1.getUserName(), post1.isPublished());

	    List<StrategyPost> postsByTopLikes = strategyPostDao.getStrategyPostByTopLikes();
		for(StrategyPost p : postsByTopLikes) {
			System.out.format("Looping StrategyPost: %s %s %s %s %s %s \n",
				p.getPostId(), p.getTitle(), p.getContent(), p.getCreated(), p.getUserName(), p.isPublished());
		}
		
		List<StrategyPost> postsByCompLevel = strategyPostDao.getStrategyPostsByCompetencyLevel(Users.CompetencyLevel.ADVANCED);
		for(StrategyPost a : postsByCompLevel) {
			System.out.format("Looping StrategyPost: %s %s %s %s %s %s \n",
				a.getPostId(), a.getTitle(), a.getContent(), a.getCreated(), a.getUserName(), a.isPublished());
		}
		List<StrategyPost> postsByCreated = strategyPostDao.getStrategyPostsByCreated(date);
		for(StrategyPost a : postsByCompLevel) {
			System.out.format("Looping StrategyPost: %s %s %s %s %s %s \n",
				a.getPostId(), a.getTitle(), a.getContent(), a.getCreated(), a.getUserName(), a.isPublished());
		}
		List<StrategyPost> postsByUser = strategyPostDao.getStrategyPostsByUserName("member1");
		for(StrategyPost a : postsByUser) {
			System.out.format("Looping StrategyPost: %s %s %s %s %s %s \n",
				a.getPostId(), a.getTitle(), a.getContent(), a.getCreated(), a.getUserName(), a.isPublished());
		}
		
//		strategyPostDao.updateContent(strategyPost1, "Amazing!");
		
//		strategyPostDao.delete(strategyPost);
		
		// credit cards instance
//		long[] creditCardId = {
//				4567456745674567L, 6789678967896789L, 5567456745674567L, 7789678967896789L,
//				4567456745674577L, 6789678967896799L, 5567456745674767L, 7789678967896889L,
//				4567456745678567L, 6789678967898789L, 5567456745678567L, 7789678967898789L	};
//		String[] userName = {"Clarissa", "Clarissa", "Clarissa", "Deborah", "Deborah", "Deborah", "Deborah", 
//		"Rebecca", "Rebecca", "Rebecca", "Rebecca", "Rebecca"};
//		String[] creditCardExpriation_str = {
//				"2024-01-01 00:00:00", "2024-12-01 00:00:00", "2025-10-01 00:00:00",		
//				"2024-03-01 00:00:00", "2025-12-01 00:00:00", "2026-10-01 00:00:00",	
//				"2024-06-01 00:00:00", "2026-12-01 00:00:00", "2027-10-01 00:00:00",	
//				"2024-09-01 00:00:00", "2027-12-01 00:00:00", "2028-10-01 00:00:00" };
//
//		int len = creditCardId.length;
//		CreditCards[] creditCards = new CreditCards[len];
//		Date[] creditCardExpriation = new Date[len];
//
//		for (int i = 0; i < len; i++) {
//			creditCardExpriation[i] = formatter.parse(creditCardExpriation_str[i]);
			// creditCards[i] = CreditCards(creditCardId[i], userName[i], creditCardExpriation[i]);
			// creditCards[i] = CreditCardsDao.create(creditCards[i]);
//		}
//		CreditCards creditCard0 = new CreditCards(creditCardId[0], membershipUser1, creditCardExpriation[0]);
//		creditCard0 = creditCardsDao.create(creditCard0);
//
//		CreditCards creditCard1 = new CreditCards(creditCardId[1], membershipUser2, creditCardExpriation[1]);
//		creditCard1 = creditCardsDao.create(creditCard1);
//
//		CreditCards crediCard2 = new CreditCards(creditCardId[8], membershipUser3, creditCardExpriation[8]);
//		creditCard2 = creditCardsDao.create(creditCard2);


		
		// Like instance
		Likes like1 = new Likes(membershipUser1, post1, date);
		
		// Repost instance
		Repost repost1 = new Repost("Amazing", membershipUser2, post1);
		
		// review and educational materials instance
		Reviews review = new Reviews(1, "first review", membershipUser1, post1);
		// Material Instance
//		EducationalMaterials m1 = new EducationalMaterials("4 Easy Steps to Get Started with Investing Stocks","This is the content for easy level investing tutorials",date,true,"Beginner");
//		EducationalMaterials m2 = new EducationalMaterials("Intermediate Investing Techniques","This is the content for intermeidate level investing tutorials",date,true,"Intermediate");
//		EducationalMaterials m3 = new EducationalMaterials("Learn the Lingo – Basic Ratios","This is the content for advanced level investing tutorials",date,true,"Advanced");
//		EducationalMaterials m4 = new EducationalMaterials("Know your risk tolerance","This is the content for expert level investing tutorials",date,true,"Expert");
	
		//Create
//		m1 = educationalMaterialsDao.create(m1);
//		m2 = educationalMaterialsDao.create(m2);
//		m3 = educationalMaterialsDao.create(m3);
//		m4 = educationalMaterialsDao.create(m4);
		
//		EducationalMaterials educationalMaterial = new EducationalMaterials(1, "titile1", "first edu content", date, true, admin1);
		Reviews review1 = reviewsDao.create(review);
		Reviews review2 = reviewsDao.getReviewById(1);
		List<Reviews> reviewsList = reviewsDao.getReviewsByPostId(1);
		for(Reviews re:reviewsList){
			 System.out.format("Looping reviews: %s %s %s %s \n",
			     re.getReviewId(), re.getReview(), re.getUser(), re.getStrategyPost());
			}

		reviewsDao.updateReview(review2, "new review");
		List<Reviews> reviews = reviewsDao.getReviewsByUserName("member1");
		for (Reviews re:reviews){
			 System.out.format("Looping reviews: %s %s %s %s \n",
			     re.getReviewId(), re.getReview(), re.getUser(), re.getStrategyPost());
			}

		reviews = reviewsDao.getReviewsByPostId(1);
		review2 = reviewsDao.delete(review2);
//		EducationalMaterials educationalMaterial1 = educationalMaterialsDao.create(educationalMaterial);
//		List<EducationalMaterials> educationalMaterials = educationalMaterialsDao.getEducationalMaterialsByUserName("admin1"); 
//		for (EducationalMaterials ed:educationalMaterials){
//			 System.out.format("Looping reviews: %s %s %s %s %s %s \n",
//			     ed.getMaterialId(), ed.getTitle(), ed.getContent(), ed.getCreated(), ed.isPublished(), ed.getUser());
//			}
//
//		educationalMaterialsDao.updateContent(educationalMaterial1, "new edu content");
//		EducationalMaterials educationalMaterial4 = educationalMaterialsDao.delete(educationalMaterial1);
		
		StocksDao stocksDao = StocksDao.getInstance();
		DailyStockRecommendationDao dailyDao = DailyStockRecommendationDao.getInstance();
		
		DailyStockRecommendation recommendation1 = new DailyStockRecommendation(1, "TSLA", date1, PositionType.Long);
		recommendation1 = dailyDao.create(recommendation1);
		DailyStockRecommendation recommendation2 = new DailyStockRecommendation(2, "TSLA", date2, PositionType.Long);
		recommendation2 = dailyDao.create(recommendation2);
		DailyStockRecommendation recommendation3 = new DailyStockRecommendation(3, "AAPL", date2, PositionType.Short);
		recommendation3 = dailyDao.create(recommendation3);
		
		// READ all distinct ticker name
		List<String> DistinctTickerNames = stocksDao.getDistinctTickerNames();
		for(String ticker : DistinctTickerNames) {
					System.out.format("Looping ticker names: %s\n", ticker);
		}
		
		// Read the begin and end row of given ticker
		List<Stocks> startAndEnd = stocksDao.getStartAndEndForTicker("TSLA");
		for(Stocks stock : startAndEnd) {
			System.out.format(
					"Looping stocks: DATE:%s OPEN:%s HIGH:%s LOW:%s CLOSE:%s ADJ_CLOSE:%s VOLUME:%s CAP_RANK:%s TICKER_NAME:%s \n", 
					stock.getDate(), stock.getOpen(), stock.getHigh(), stock.getLow(), stock.getClose(), stock.getAdjClose(), stock.getVolume(), stock.getStockCapRank(), stock.getTickerName());
		}
		
		// Get the return percent based on given ticker
		Double returnPercent = stocksDao.getReturnPercentForTicker("TSLA");
		System.out.format("The return percent based on given ticker: %s", returnPercent);
		
		
		// Read the recommendations based on the ticker name
		List<DailyStockRecommendation> recommendations = dailyDao.getRecommendationByTicker("TSLA");
		for(DailyStockRecommendation recom: recommendations) {
			System.out.format(
					"Looping recommendations: %s %s %s %s \n",
					recom.getDailyStockRecommendationID(), recom.getTickerName(), recom.getDate(), recom.getPosition()
				);
		}
		
		// Read the recommendations based on the date
		List<DailyStockRecommendation> recommendations1 = dailyDao.getRecommendationByDate(date2);
		for(DailyStockRecommendation recom: recommendations1) {
			System.out.format(
					"Looping recommendations: %s %s %s %s \n",
					recom.getDailyStockRecommendationID(), recom.getTickerName(), recom.getDate(), recom.getPosition()
				);
		}
	}
}
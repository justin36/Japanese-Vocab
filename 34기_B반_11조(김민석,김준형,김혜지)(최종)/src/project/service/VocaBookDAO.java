package project.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import project.vo.Member;
import project.vo.Voca;
import project.vo.VocaList;

public class VocaBookDAO implements VocaBookMapper {
	private SqlSessionFactory factory = MyBatisConfig.getSqlSessionFactory();
	
	@Override
	// ȸ������
	public int registerAccount(Member m) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		int result = 0; // result �� 0 �� �Ǹ� �ȵȰ���
		// UI ���� üũ�� 1 ���� 0������ üũ
		try {
			result = vbm.registerAccount(m);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	// ���̵� �ߺ�üũ
	public Member checkId(String id) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		Member result = null; // result �� 0 �� �Ǹ� �ȵȰ���
		// UI ���� üũ�� 1 ���� 0������ üũ
		try {
			result = vbm.checkId(id);
		} finally {
			session.close();
		}
		return result;
	}

	// �α���
	@Override
	public Member login(Member m) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		Member result = null; // result �� 0 �� �Ǹ� �ȵȰ���
		// UI ���� üũ�� 1 ���� 0������ üũ
		try {
			result = vbm.login(m);
		} finally {
			session.close();
		}
		return result;
	}

	// �⼮����
	@Override
	public int addAttendance(String id) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		int result = 0; // result �� 0 �� �Ǹ� �ȵȰ���
		// UI ���� üũ�� 1 ���� 0������ üũ
		try {
			result = vbm.addAttendance(id);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	// �ܾ��� ����Ƚ�� ����
	@Override
	public int addStudyCount(VocaList value2) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		int result = 0; // result �� 0 �� �Ǹ� �ȵȰ���
		// UI ���� üũ�� 1 ���� 0������ üũ
		try {
			result = vbm.addStudyCount(value2);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	// ���ã��
	@Override
	public Member findPw(String id) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		Member result = null; // result �� 0 �� �Ǹ� �ȵȰ���
		// UI ���� üũ�� 1 ���� 0������ üũ
		try {
			result = vbm.findPw(id);
		} finally {
			session.close();
		}
		return result;
	}

	// ȸ��Ż��
	public Integer deleteAccount(Member m) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		Integer result = 0; // result �� 0 �� �Ǹ� �ȵȰ���
		// UI ���� üũ�� 1 ���� 0������ üũ
		try {
			result = vbm.deleteAccount(m);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	// �ܾ��� ���
	@Override
	public int insertList(VocaList voca) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		int result = 0; // result �� 0 �� �Ǹ� �ȵȰ���
		// UI ���� üũ�� 1 ���� 0������ üũ
		try {
			result = vbm.insertList(voca);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	// �ܾ��� �Ӽ��� ���
	public int insertWordInList(Voca words) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		int result = 0; // result �� 0 �� �Ǹ� �ȵȰ���
		// UI ���� üũ�� 1 ���� 0������ üũ
		try {
			result = vbm.insertWordInList(words);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	// �ܾ��帮��Ʈ �����ֱ�(������¥��)
	public ArrayList<VocaList> loadListByDate(String id) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		ArrayList<VocaList> result = null; // result �� 0 �� �Ǹ� �ȵȰ���
		// UI ���� üũ�� 1 ���� 0������ üũ
		try {
			result = vbm.loadListByDate(id);
		} finally {
			session.close();
		}
		return result;
	}

	// �ܾ��� �ҷ�����(�̸���)
	@Override
	public ArrayList<Voca> searchVocaList(VocaList find) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		ArrayList<Voca> result = null; // result �� 0 �� �Ǹ� �ȵȰ���
		// UI ���� üũ�� 1 ���� 0������ üũ
		try {
			result = vbm.searchVocaList(find);
		} finally {
			session.close();
		}
		return result;
	}

	// �ܾ��� ����
	@Override
	public int deleteList(int listNo) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		int result = 0; // result �� 0 �� �Ǹ� �ȵȰ���
		// UI ���� üũ�� 1 ���� 0������ üũ
		try {
			result = vbm.deleteList(listNo);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	// ���̵� �����ִ� ��� �ܾ��� ��ȯ
	@Override
	public ArrayList<Voca> getAllList(String id) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		ArrayList<Voca> result = null; // result �� 0 �� �Ǹ� �ȵȰ���
		// UI ���� üũ�� 1 ���� 0������ üũ
		try {
			result = vbm.getAllList(id);
		} finally {
			session.close();
		}
		return result;
	}

	// �ܾ��� ����
	public int modifyList(Voca voca) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		int result = 0; // result �� 0 �� �Ǹ� �ȵȰ���
		// UI ���� üũ�� 1 ���� 0������ üũ
		try {
			result = vbm.modifyList(voca);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	public int checkAnswer(Voca answer) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		int result = 0; // result �� 0 �� �Ǹ� �ȵȰ���
		// UI ���� üũ�� 1 ���� 0������ üũ
		try {
			result = vbm.checkAnswer(answer);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}
	
	// ���󰡳� ����. ���� �߰�
	public int checkAnswer2(Voca answer) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		int result = 0; // result �� 0 �� �Ǹ� �ȵȰ���
		// UI ���� üũ�� 1 ���� 0������ üũ
		try {
			result = vbm.checkAnswer2(answer);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}


	// �����Ʈ
	public ArrayList<Voca> seeWrongWords(VocaList find) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		ArrayList<Voca> result = null; // result �� 0 �� �Ǹ� �ȵȰ���
		// UI ���� üũ�� 1 ���� 0������ üũ
		try {
			result = vbm.seeWrongWords(find);
		} finally {
			session.close();
		}
		return result;
	}

	// �ܾ� ����
	public int deleteVoca(int vocaNo) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		int result = 0; // result �� 0 �� �Ǹ� �ȵȰ���
		// UI ���� üũ�� 1 ���� 0������ üũ
		try {
			result = vbm.deleteVoca(vocaNo);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	@Override // �����ϱ�
	public int sharing(int sharing, int listNo) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		int result = 0;
		try {
			result = vbm.sharing(sharing, listNo);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	@Override // ������ �޸��� �ҷ�����
	public ArrayList<VocaList> getSharing(String id) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		ArrayList<VocaList> result = null;
		try {
			result = vbm.getSharing(id);
		} finally {
			session.close();
		}
		return result;
	}

	@Override // ��������
	public int notice() {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		int result = 0;
		try {
			result = vbm.notice();
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	@Override // ��������
	public int noticeoff(Member m) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		int result = 0;
		try {
			result = vbm.noticeoff(m);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	public int changeListName(VocaList value2) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		int result = 0;
		try {
			result = vbm.changeListName(value2);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}
	public int changeListName2(VocaList value2) {
		SqlSession session = factory.openSession();
		VocaBookMapper vbm = session.getMapper(VocaBookMapper.class);
		int result = 0;
		try {
			result = vbm.changeListName2(value2);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	
}

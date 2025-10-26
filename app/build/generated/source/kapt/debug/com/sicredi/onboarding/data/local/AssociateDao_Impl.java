package com.sicredi.onboarding.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.sicredi.onboarding.data.model.Associate;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AssociateDao_Impl implements AssociateDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Associate> __insertionAdapterOfAssociate;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<Associate> __deletionAdapterOfAssociate;

  private final EntityDeletionOrUpdateAdapter<Associate> __updateAdapterOfAssociate;

  public AssociateDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAssociate = new EntityInsertionAdapter<Associate>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `associates` (`id`,`nome`,`cpf`,`email`,`telefone`,`endereco`,`rendaMensal`,`dataNascimento`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Associate entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNome() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getNome());
        }
        if (entity.getCpf() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getCpf());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getEmail());
        }
        if (entity.getTelefone() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTelefone());
        }
        if (entity.getEndereco() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getEndereco());
        }
        if (entity.getRendaMensal() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getRendaMensal());
        }
        final Long _tmp = __converters.toEpoch(entity.getDataNascimento());
        if (_tmp == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, _tmp);
        }
        statement.bindLong(9, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfAssociate = new EntityDeletionOrUpdateAdapter<Associate>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `associates` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Associate entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfAssociate = new EntityDeletionOrUpdateAdapter<Associate>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `associates` SET `id` = ?,`nome` = ?,`cpf` = ?,`email` = ?,`telefone` = ?,`endereco` = ?,`rendaMensal` = ?,`dataNascimento` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Associate entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNome() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getNome());
        }
        if (entity.getCpf() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getCpf());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getEmail());
        }
        if (entity.getTelefone() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTelefone());
        }
        if (entity.getEndereco() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getEndereco());
        }
        if (entity.getRendaMensal() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getRendaMensal());
        }
        final Long _tmp = __converters.toEpoch(entity.getDataNascimento());
        if (_tmp == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, _tmp);
        }
        statement.bindLong(9, entity.getCreatedAt());
        statement.bindLong(10, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final Associate entity, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfAssociate.insertAndReturnId(entity);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final Associate entity, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfAssociate.handle(entity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final Associate entity, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAssociate.handle(entity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Associate>> observeAll() {
    final String _sql = "SELECT * FROM associates ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"associates"}, new Callable<List<Associate>>() {
      @Override
      @NonNull
      public List<Associate> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfCpf = CursorUtil.getColumnIndexOrThrow(_cursor, "cpf");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfTelefone = CursorUtil.getColumnIndexOrThrow(_cursor, "telefone");
          final int _cursorIndexOfEndereco = CursorUtil.getColumnIndexOrThrow(_cursor, "endereco");
          final int _cursorIndexOfRendaMensal = CursorUtil.getColumnIndexOrThrow(_cursor, "rendaMensal");
          final int _cursorIndexOfDataNascimento = CursorUtil.getColumnIndexOrThrow(_cursor, "dataNascimento");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Associate> _result = new ArrayList<Associate>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Associate _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpNome;
            if (_cursor.isNull(_cursorIndexOfNome)) {
              _tmpNome = null;
            } else {
              _tmpNome = _cursor.getString(_cursorIndexOfNome);
            }
            final String _tmpCpf;
            if (_cursor.isNull(_cursorIndexOfCpf)) {
              _tmpCpf = null;
            } else {
              _tmpCpf = _cursor.getString(_cursorIndexOfCpf);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpTelefone;
            if (_cursor.isNull(_cursorIndexOfTelefone)) {
              _tmpTelefone = null;
            } else {
              _tmpTelefone = _cursor.getString(_cursorIndexOfTelefone);
            }
            final String _tmpEndereco;
            if (_cursor.isNull(_cursorIndexOfEndereco)) {
              _tmpEndereco = null;
            } else {
              _tmpEndereco = _cursor.getString(_cursorIndexOfEndereco);
            }
            final Double _tmpRendaMensal;
            if (_cursor.isNull(_cursorIndexOfRendaMensal)) {
              _tmpRendaMensal = null;
            } else {
              _tmpRendaMensal = _cursor.getDouble(_cursorIndexOfRendaMensal);
            }
            final LocalDate _tmpDataNascimento;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDataNascimento)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDataNascimento);
            }
            _tmpDataNascimento = __converters.fromEpoch(_tmp);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new Associate(_tmpId,_tmpNome,_tmpCpf,_tmpEmail,_tmpTelefone,_tmpEndereco,_tmpRendaMensal,_tmpDataNascimento,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getById(final long id, final Continuation<? super Associate> $completion) {
    final String _sql = "SELECT * FROM associates WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Associate>() {
      @Override
      @Nullable
      public Associate call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfCpf = CursorUtil.getColumnIndexOrThrow(_cursor, "cpf");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfTelefone = CursorUtil.getColumnIndexOrThrow(_cursor, "telefone");
          final int _cursorIndexOfEndereco = CursorUtil.getColumnIndexOrThrow(_cursor, "endereco");
          final int _cursorIndexOfRendaMensal = CursorUtil.getColumnIndexOrThrow(_cursor, "rendaMensal");
          final int _cursorIndexOfDataNascimento = CursorUtil.getColumnIndexOrThrow(_cursor, "dataNascimento");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final Associate _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpNome;
            if (_cursor.isNull(_cursorIndexOfNome)) {
              _tmpNome = null;
            } else {
              _tmpNome = _cursor.getString(_cursorIndexOfNome);
            }
            final String _tmpCpf;
            if (_cursor.isNull(_cursorIndexOfCpf)) {
              _tmpCpf = null;
            } else {
              _tmpCpf = _cursor.getString(_cursorIndexOfCpf);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpTelefone;
            if (_cursor.isNull(_cursorIndexOfTelefone)) {
              _tmpTelefone = null;
            } else {
              _tmpTelefone = _cursor.getString(_cursorIndexOfTelefone);
            }
            final String _tmpEndereco;
            if (_cursor.isNull(_cursorIndexOfEndereco)) {
              _tmpEndereco = null;
            } else {
              _tmpEndereco = _cursor.getString(_cursorIndexOfEndereco);
            }
            final Double _tmpRendaMensal;
            if (_cursor.isNull(_cursorIndexOfRendaMensal)) {
              _tmpRendaMensal = null;
            } else {
              _tmpRendaMensal = _cursor.getDouble(_cursorIndexOfRendaMensal);
            }
            final LocalDate _tmpDataNascimento;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDataNascimento)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDataNascimento);
            }
            _tmpDataNascimento = __converters.fromEpoch(_tmp);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new Associate(_tmpId,_tmpNome,_tmpCpf,_tmpEmail,_tmpTelefone,_tmpEndereco,_tmpRendaMensal,_tmpDataNascimento,_tmpCreatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

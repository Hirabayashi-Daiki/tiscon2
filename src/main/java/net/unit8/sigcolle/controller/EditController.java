package net.unit8.sigcolle.controller;

import javax.inject.Inject;
import javax.transaction.Transactional;

import enkan.collection.Multimap;
import enkan.component.doma2.DomaProvider;
import enkan.data.HttpResponse;
import enkan.data.Session;
import kotowari.component.TemplateEngine;
import net.unit8.sigcolle.auth.LoginUserPrincipal;
import net.unit8.sigcolle.dao.UserDao;
import net.unit8.sigcolle.form.EditForm;
import net.unit8.sigcolle.form.RegisterForm;
import net.unit8.sigcolle.model.User;

import static enkan.util.HttpResponseUtils.RedirectStatusCode.SEE_OTHER;
import static enkan.util.HttpResponseUtils.redirect;

/**
 * @author maeken
 */
public class EditController {
    @Inject
    private TemplateEngine templateEngine;

    @Inject
    private DomaProvider domaProvider;

    private static final String EMAIL_ALREADY_EXISTS = "このメールアドレスは既に登録されています。";

    /**
     * ユーザー登録画面表示.
     *
     * @return HttpResponse
     */
    public HttpResponse index(Session session)
    {
        LoginUserPrincipal principal = (LoginUserPrincipal) session.get("principal");
        Long loginUserID = principal.getUserId();

        UserDao userDao = domaProvider.getDao(UserDao.class);
        User user = userDao.selectByUserId(loginUserID);

        return templateEngine.render("user/edit", "user", new EditForm());
    }

    /**
     * ユーザー登録処理.
     *
     * @param form 画面入力されたユーザー情報
     * @return HttpResponse
     */
    @Transactional
    public HttpResponse edit(EditForm form) {

        if (form.hasErrors()) {
            return templateEngine.render("user/edit", "user", form);
        }

        UserDao userDao = domaProvider.getDao(UserDao.class);

        // メールアドレス重複チェック
//        if (userDao.countByEmail(form.getEmail()) != 0) {
//            form.setErrors(Multimap.of("email", EMAIL_ALREADY_EXISTS));
//            return templateEngine.render("user/register",
//                    "user", form
//            );
//        }
        Session session = new Session();
        User loginUser = userDao.selectByEmail(form.getEmail());

        User user = new User();
        user.setUserId(loginUser.getUserId());
        user.setLastName(form.getLastName());
        user.setFirstName(form.getFirstName());
        user.setEmail(form.getEmail());
        user.setPass(form.getPass());

        //userDao.delete(user);
        userDao.update(user);

        //Session session = new Session();
        //User loginUser = userDao.selectByEmail(form.getEmail());
        session.put(
                "principal",
                new LoginUserPrincipal(loginUser.getUserId(), loginUser.getLastName() + " " + loginUser.getFirstName())
        );

        HttpResponse response = redirect("/", SEE_OTHER);
        response.setSession(session);
        return response;
    }
}
